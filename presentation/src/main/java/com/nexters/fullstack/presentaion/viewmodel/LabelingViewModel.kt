package com.nexters.fullstack.presentaion.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.Input
import com.nexters.fullstack.Output
import com.nexters.fullstack.presentaion.mapper.LabelSourceMapper
import com.nexters.fullstack.presentaion.mapper.LabelingMapper
import com.nexters.fullstack.presentaion.mapper.PresenterLocalFileMapper
import com.nexters.fullstack.presentaion.model.*
import com.nexters.fullstack.domain.entity.DomainUserImage
import com.nexters.fullstack.domain.usecase.ImageLabelingUseCase
import com.nexters.feature.ui.data.pallet.PalletItem
import com.nexters.fullstack.domain.entity.LocalImageDomain
import com.nexters.fullstack.domain.entity.DomainUserLabel
import com.nexters.fullstack.domain.usecase.GetLabelManagementUseCase
import com.nexters.fullstack.domain.usecase.base.BaseUseCase
import com.nexters.fullstack.presentaion.mapper.LocalMainLabelMapper
import com.nexters.fullstack.util.SingleLiveData
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class LabelingViewModel(
//    private val labelingUseCase: LabelingUseCase,
    private val getLabelManagementUseCase: GetLabelManagementUseCase,
    loadImageUseCase: BaseUseCase<Unit, Maybe<List<DomainUserImage>>>,
    private val imageLabelingUseCase: ImageLabelingUseCase
) : BaseViewModel() {
    private val _viewState = MutableLiveData<ViewState>()
    private val _finish = MutableLiveData<Unit>()
    private val _isEmptyLabel = MutableLiveData(true)
    private val _labels = MutableLiveData<List<LabelSource>>()
    private val _toastMessage = SingleLiveData<String>()

    private val _images = MutableLiveData<List<Map<DomainUserLabel, List<LocalImageDomain>>>>()

    private val items = mutableListOf<MutableMap<DomainUserLabel, MutableList<LocalImageDomain>>>()

    val labelingMap = mutableMapOf<DomainUserLabel, MutableList<LocalImageDomain>>()

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
        override fun getLocalLabels(): LiveData<List<LabelSource>> = _labels
        override fun didWriteCreateLabelForm(): LiveData<Boolean> = _didWriteLabelInfo
        override fun getLabelQuery(): LiveData<String> = labelQuery
        override fun getImages(): LiveData<List<Map<DomainUserLabel, List<LocalImageDomain>>>> =
            _images

        override fun getToastMessage(): LiveData<String> = _toastMessage
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
                        getLabelManagementUseCase.insertOrUpdate(mapper)
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
                        getLabelManagementUseCase.insertOrUpdate(mapper)
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
                    getLabelManagementUseCase.buildUseCase(Unit)
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

        override fun clickLabelingButton(didClickList: List<LabelSource>, file: PresentLocalFile) {
            if (file.url.isEmpty()) {
                return
            }
            if (didClickList.isNullOrEmpty()) {
                return
            }
            val localFileMapper = PresenterLocalFileMapper.toData(file)
            val labelMapper = LabelSourceMapper.toData(didClickList)

            disposable.add(
                imageLabelingUseCase.buildUseCase(
                    DomainUserImage(labelMapper, localFileMapper)
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
    }

    init {
        val labels = getLabelManagementUseCase.buildUseCase(Unit).cache()

        val labelTextCache = _labelText.debounce(1000L, TimeUnit.MILLISECONDS).cache()

        val clickLabelCache = _clickedLabel.cache()

        disposable.addAll(
            labels
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ localLabels ->
                    if (localLabels.isNotEmpty()) {
                        _isEmptyLabel.value = false
                        Log.e("라벨스", localLabels.toString())
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

//            clickLabelCache
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                }, { it.printStackTrace() })
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

    interface LabelingOutput : Output {
        fun viewState(): LiveData<ViewState>

        fun finish(): LiveData<Unit>

        fun isEmptyLocalLabel(): LiveData<Boolean>

        fun getLocalLabels(): LiveData<List<LabelSource>>

        fun didWriteCreateLabelForm(): LiveData<Boolean>

        fun getLabelQuery(): LiveData<String>

        fun getImages(): LiveData<List<Map<DomainUserLabel, List<LocalImageDomain>>>>
        fun getToastMessage(): LiveData<String>
    }

    interface LabelingInput : Input {
        fun clickAppbar(viewState: ViewState)

        fun clickLabel(palletItem: PalletItem)

        fun clickSaveButton(labelingState: LabelingState)

        fun clickLabelAddButton()

        fun clickCancelButton()

        fun clickLabelingButton(didClickList: List<LabelSource>, file: PresentLocalFile)

        fun setDidLabelingState(isDidLabeled: Boolean)
        fun setToastMessage(message: String)
    }

    fun onCreateView(data: MainMakeLabelSource) {
        this.makeMainLabelSource = data
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}