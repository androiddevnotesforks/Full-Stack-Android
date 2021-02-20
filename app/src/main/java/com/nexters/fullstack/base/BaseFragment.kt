package com.nexters.fullstack.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<VB : ViewDataBinding, VM : ViewModel> : Fragment() {
    protected lateinit var binding: VB
        private set

    internal val disposable = CompositeDisposable()
    //layout
    abstract val layoutRes: Int

    abstract val viewModel : VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)

        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

    fun bind(body: VB.() -> Unit) {
        binding.run(body)
        binding.executePendingBindings()
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
}