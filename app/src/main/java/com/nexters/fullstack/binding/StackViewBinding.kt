package com.nexters.fullstack.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.nexters.fullstack.presentaion.source.State
import com.nexters.fullstack.viewmodel.MainViewModel
import com.yuyakaido.android.cardstackview.CardStackView

@BindingAdapter(
    "app:approve",
    "app:targetView",
    "app:onApproveButtonClickListener",
    requireAll = true
)
fun ImageView.setOnApproveButtonClickListener(
    isSwipe: Boolean?,
    cardStackView: CardStackView?,
    emit: MainViewModel.MainInput?
) {
    var data = isSwipe
    setOnClickListener {
        data = false
        if (data != false) {
            return@setOnClickListener
        }
        cardStackView?.swipe()
        emit?.onClickedButton(State.Approve)
    }
}

@BindingAdapter("app:onRejectButtonClickListener")
fun ImageView.setOnRejectButtonClickListener(emit: MainViewModel.MainInput?) {
    setOnClickListener {
        emit?.onClickedButton(State.Reject)
    }
}