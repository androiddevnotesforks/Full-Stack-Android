package com.nexters.fullstack.presentaion.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.presentaion.BaseViewModel
import com.nexters.fullstack.presentaion.Input
import com.nexters.fullstack.presentaion.Output
import com.nexters.fullstack.presentaion.mapper.LabelSourceMapper
import com.nexters.fullstack.presentaion.mapper.PresenterLocalFileMapper
import com.nexters.fullstack.presentaion.model.*
import com.nexters.fullstack.domain.entity.ImageEntity
import com.nexters.fullstack.domain.usecase.RequestLabeling
import com.nexters.feature.ui.data.pallet.PalletItem
import com.nexters.fullstack.domain.entity.FileImageEntity
import com.nexters.fullstack.domain.entity.LabelEntity
import com.nexters.fullstack.domain.usecase.CreateLabel
import com.nexters.fullstack.domain.usecase.GetLabels
import com.nexters.fullstack.domain.usecase.LoadImageUseCase
import com.nexters.fullstack.presentaion.mapper.LabelingMapper
import com.nexters.fullstack.presentaion.mapper.LocalMainLabelMapper
import com.nexters.fullstack.util.SingleLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class LabelingViewModel(
    private val getLabels: GetLabels,
    private val createLabel : CreateLabel,
    private val loadImageUseCase: LoadImageUseCase,
    private val requestLabeling: RequestLabeling
) : BaseViewModel() {
    private val _viewState = MutableLiveData<ViewState>()
    private val _finish = MutableLiveData<Unit>()
    private val _isEmptyLabel = MutableLiveData(true)
    private val _labels = MutableLiveData<List<LabelViewData>>()
    private val _toastMessage = SingleLiveData<String>()

    private val _images = MutableLiveData<List<Map<LabelEntity, List<FileImageEntity>>>>()

    private val items = mutableListOf<MutableMap<LabelEntity, MutableList<FileImageEntity>>>()

    private val _createLabel = SingleLiveData<Unit>()

    val labelingMap = mutableMapOf<LabelEntity, MutableList<FileImageEntity>>()

    val _didWriteLabelInfo = MutableLiveData(false)
    private var makeMainLabelSource: MainMakeLabelSource? = null
    private val _clickedLabel = PublishSubject.create<PalletItem>()
    private val _labelText = PublishSubject.create<String>()
    val labelQuery = MutableLiveData("")

    private val disposable = CompositeDisposable()

    fun onTextChanged(s: CharSequence) = _labelText.onNext(s.toString())

    val output = object : LabelingOutput {
        override fun viewState(): LiveData<ViewState> = _viewState
        override fun finish(): LiveData<Unit> = _finish
        override fun isEmptyLocalLabel(): LiveData<Boolean> = _isEmptyLabel
        override fun getLocalLabels(): LiveData<List<LabelViewData>> = _labels
        override fun didWriteCreateLabelForm(): LiveData<Boolean> = _didWriteLabelInfo
        override fun getLabelQuery(): LiveData<String> = labelQuery
        override fun getImages(): LiveData<List<Map<LabelEntity, List<FileImageEntity>>>> =
            _images

        override fun getToastMessage(): LiveData<String> = _toastMessage
        override fun goToCreateLabel(): LiveData<Unit> = _createLabel
    }

    val input = object : LabelingInput {
        override fun clickAppbar(viewState: ViewState) {
            _viewState.value = viewState
        }

        override fun clickLabel(palletItem: PalletItem) = _clickedLabel.onNext(palletItem)

        override fun clickSaveButton(labelingState: LabelingState) {
            makeMainLabelSource?.let { source ->
                val mapper = LabelingMapper.fromData(source)

                val labeling = when (labelingState) {
                    LabelingState.CREATE -> {
                        createLabel.buildUseCase(mapper)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                _toastMessage.value = "${mapper.text}라벨이 생성되었습니다."
                                _finish.value = Unit
                            }, {
                                it.printStackTrace()
                            })
                    }
                    LabelingState.UPDATE -> {
                        createLabel.buildUseCase(mapper)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                _toastMessage.value = "${mapper.text}라벨이 수정되었습니다."
                                _finish.value = Unit
                            }, {
                                it.printStackTrace()
                            })
                    }
                }

                disposable.addAll(
                    labeling,
                    getLabels.buildUseCase(Unit)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ localLabels ->
                            if (localLabels.isNotEmpty()) {
                                _isEmptyLabel.value = false
                                _labels.value = localLabels.map {
                                    LocalMainLabelMapper.fromData(it)
                                }
                            } else {
                                _isEmptyLabel.value = true
                            }
                        }, { it.printStackTrace() }),
                )
            } ?: Log.e("labelSourceError", "makeMainLabelSource is Null")
        }

        override fun clickLabelAddButton() {
            _viewState.value = ViewState.Add
        }

        override fun clickCancelButton() {
            _finish.value = Unit
        }

        override fun clickLabelingButton(didClickList: List<LabelViewData>, image: FileImageViewData) {
            if (image.url.isEmpty()) {
                return
            }
            if (didClickList.isNullOrEmpty()) {
                return
            }
            val localFileMapper = PresenterLocalFileMapper.toData(image)
            val labelMapper = LabelSourceMapper.toData(didClickList)

            disposable.add(
                requestLabeling.buildUseCase(
                    ImageEntity(labelMapper, localFileMapper)
                ).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _finish.value = Unit
                    }, { it.printStackTrace() })
            )
        }

        override fun setDidLabelingState(isDidLabeled: Boolean) {
            _didWriteLabelInfo.value = isDidLabeled
        }

        override fun setToastMessage(message: String) {
            _toastMessage.value = message
        }

        override fun goToCreateLabel() = _createLabel.call()
    }

    init {
        val labels = getLabels.buildUseCase(Unit).cache()

        val labelTextCache = _labelText.debounce(1000L, TimeUnit.MILLISECONDS).cache()

        val clickLabelCache = _clickedLabel.cache()

        disposable.addAll(
            labels
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ localLabels ->
                    if (localLabels.isNotEmpty()) {
                        _isEmptyLabel.value = false
                        _labels.value = localLabels.map {
                            LocalMainLabelMapper.fromData(it)
                        }
                    } else {
                        _isEmptyLabel.value = true
                    }
                }, { it.printStackTrace() }),

            loadImageUseCase.buildUseCase(Unit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.forEach { domainUserImage ->
                        domainUserImage.labels.forEach { label ->
                            if (labelingMap.containsKey(label)) {
                                val images = labelingMap[label]
                                images?.let { mutableImage ->
                                    if (!mutableImage.contains(domainUserImage.image)) {
                                        mutableImage.add(domainUserImage.image)
                                    }
                                } ?: run {
                                    labelingMap[label] = mutableListOf(domainUserImage.image)
                                }
                            } else {
                                labelingMap[label] = mutableListOf(domainUserImage.image)
                            }
                        }
                        items.add(labelingMap)
                    }
                    _images.value = items
                }, { it.printStackTrace() }),

            Observable.combineLatest(
                labelTextCache,
                clickLabelCache,
                ::MainMakeLabelSource
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ labelSource ->
                    val result = didWriteLabelInfo(labelSource)
                    makeMainLabelSource = labelSource
                    _didWriteLabelInfo.value = result
                }, { it.printStackTrace() }),
        )
        _viewState.value = ViewState.Selected
    }

    private fun didWriteLabelInfo(mainMakeLabelSource: MainMakeLabelSource): Boolean {
        var result = false
        return if (mainMakeLabelSource.labelText.isBlank()) {
            result
        } else {
            result = true
            result
        }
    }

    fun fetchImages() {
        disposable.addAll(
            loadImageUseCase.buildUseCase(Unit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.forEach { domainUserImage ->
                        domainUserImage.labels.forEach { label ->
                            if (labelingMap.containsKey(label)) {
                                val images = labelingMap[label]
                                images?.let { mutableImage ->
                                    if (!mutableImage.contains(domainUserImage.image)) {
                                        mutableImage.add(domainUserImage.image)
                                    }
                                } ?: run {
                                    labelingMap[label] = mutableListOf(domainUserImage.image)
                                }
                            } else {
                                labelingMap[label] = mutableListOf(domainUserImage.image)
                            }
                        }
                        items.add(labelingMap)
                    }
                    _images.value = items
                }, { it.printStackTrace() })
        )
    }

    fun fetchLabels() {
        disposable.add(getLabels.buildUseCase(Unit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ localLabels ->
                if (localLabels.isNotEmpty()) {
                    _isEmptyLabel.value = false
                    _labels.value =  localLabels.map {
                        LocalMainLabelMapper.fromData(it)
                    }
                } else {
                    _isEmptyLabel.value = true
                }
            }, { it.printStackTrace() })
        )
    }

    interface LabelingOutput : Output {
        fun viewState(): LiveData<ViewState>

        fun finish(): LiveData<Unit>

        fun isEmptyLocalLabel(): LiveData<Boolean>

        fun getLocalLabels(): LiveData<List<LabelViewData>>

        fun didWriteCreateLabelForm(): LiveData<Boolean>

        fun getLabelQuery(): LiveData<String>

        fun getImages(): LiveData<List<Map<LabelEntity, List<FileImageEntity>>>>
        fun getToastMessage(): LiveData<String>
        fun goToCreateLabel(): LiveData<Unit>
    }

    interface LabelingInput : Input {
        fun clickAppbar(viewState: ViewState)

        fun clickLabel(palletItem: PalletItem)

        fun clickSaveButton(labelingState: LabelingState)

        fun clickLabelAddButton()

        fun clickCancelButton()

        fun clickLabelingButton(didClickList: List<LabelViewData>, file: FileImageViewData)

        fun setDidLabelingState(isDidLabeled: Boolean)
        fun setToastMessage(message: String)

        fun goToCreateLabel()
    }

    fun onCreateView(data: MainMakeLabelSource) {
        this.makeMainLabelSource = data
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}