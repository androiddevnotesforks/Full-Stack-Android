package com.nexters.fullstack.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.nexters.fullstack.BR
import com.nexters.fullstack.Constants
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentMyalbumBinding
import com.nexters.fullstack.R
import com.nexters.fullstack.mapper.LocalImageMapper
import com.nexters.fullstack.mapper.LocalMainLabelMapper
import com.nexters.fullstack.mapper.local.LocalLabelMapper
import com.nexters.fullstack.source.LabelingImage
import com.nexters.fullstack.source.data.LocalImageDomain
import com.nexters.fullstack.source.local.DomainUserLabel
import com.nexters.fullstack.ui.activity.AlbumActivitybyColor
import com.nexters.fullstack.ui.activity.CreateLabelActivity
import com.nexters.fullstack.ui.adapter.LocalImageAdapter
import com.nexters.fullstack.ui.adapter.listener.ItemClickListener
import com.nexters.fullstack.ui.adapter.listener.OnClickItemDelegate
import com.nexters.fullstack.ui.holder.LocalImageViewHolder
import com.nexters.fullstack.ui.widget.bottomsheet.LabelManagerBottomSheetDialog
import com.nexters.fullstack.ui.widget.bottomsheet.recyclerview.GridLayoutRecyclerOnScrollListener
import com.nexters.fullstack.viewmodel.LabelingViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.concurrent.TimeUnit

class MyAlbumFragment : BaseFragment<FragmentMyalbumBinding, LabelingViewModel>(),
    ItemClickListener, OnClickItemDelegate {

    override val layoutRes: Int = R.layout.fragment_myalbum

    override val viewModel: LabelingViewModel by sharedViewModel()

    private val addLabelButtonSubject = BehaviorSubject.create<Unit>()

    private val offsetRecyclerListener by lazy {
        GridLayoutRecyclerOnScrollListener(binding.toolbar, binding.addLabel, binding.title)
    }

    init {
        /**
         * 액티비티 중복 호출 방지.
         * todo MainActivity  에서 객체가 한번 다 만들어지니까 또 호출할 땐 중복 호출이 안됌.
         */
        disposable.add(
            addLabelButtonSubject
                .throttleFirst(1000L, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { startActivity(Intent(requireContext(), CreateLabelActivity::class.java)) },
                    { it.printStackTrace() }
                )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        onObserve()
        setOnInitClickListener()
        bind {
            setVariable(BR.vm, viewModel)
            setVariable(BR.event, this@MyAlbumFragment)
            setVariable(BR.delegate, this@MyAlbumFragment)
        }
    }

    private fun initView() {
        binding.rvUserImage.addOnScrollListener(offsetRecyclerListener)
    }

    private fun setOnInitClickListener() {
        binding.addLabel.setOnClickListener {
            addLabelButtonSubject.onNext(Unit)
        }
    }

    private fun onObserve() {
        with(viewModel.output) {
            getToastMessage().observe(this@MyAlbumFragment.viewLifecycleOwner) { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        fun getInstance(): MyAlbumFragment {
            return MyAlbumFragment()
        }
    }

    override fun onDestroy() {
        binding.rvUserImage.removeOnScrollListener(offsetRecyclerListener)
        super.onDestroy()
    }

    /**
     * Album Lead More Click Event
     **/
    override fun onClick(item: DomainUserLabel) {
        LabelManagerBottomSheetDialog.getInstance(
            LocalLabelMapper.toDomain(item)
        ).show(requireActivity().supportFragmentManager, this.tag)
    }

    override fun onClickItem(item: LabelingImage) {
        val intent = Intent(context, AlbumActivitybyColor::class.java)
        val imageMapper = item.localImages.map(LocalImageMapper::toDomain)
        val labelMapper = LocalMainLabelMapper.toData(item.domainLabel)
        intent.putExtra(Constants.KEY_IMAGES, imageMapper.toTypedArray())
        intent.putExtra(Constants.LABEL, labelMapper)
        startActivity(intent)
    }

}