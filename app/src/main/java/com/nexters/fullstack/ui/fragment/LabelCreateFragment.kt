package com.nexters.fullstack.ui.fragment

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.ViewModel
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentLabelCreateBinding
import com.nexters.fullstack.widget.LabelColorBottomSheet
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelCreateFragment : BaseFragment<FragmentLabelCreateBinding, ViewModel>() {

    override val viewModel: ViewModel by viewModel()

    override val layoutRes: Int = R.layout.fragment_label_create

    var isKeyboardShowing = false
    var keypadBaseHeight = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewInit()

        binding.selectColor.setOnClickListener {
            LabelColorBottomSheet(requireContext()).show()
        }
    }

    private fun viewInit() {
        view?.viewTreeObserver?.addOnGlobalLayoutListener {
            val rect = Rect()
            view?.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view?.rootView?.height // rootView에 대한 전체 높이

            val keypadHeight = screenHeight?.minus(rect.bottom)



            if (keypadBaseHeight == 0) {
                // 기기마다 소프트 키보드가 구현되는 방식이 다름. 화면 아래에 숨어있거나 invisible로 구현되어 있음. 그차이로 인해 기기마다 약간씩 레이아웃이 틀어지는데 그것을 방지하기 위해 필요함.
                keypadBaseHeight = keypadHeight ?: 2
            }


            if (keypadHeight != null) {
                if (keypadHeight > screenHeight * 0.15) {
                    // 키보드가 대략 전체 화면의 15% 정도 높이 이상으로 올라온다.
                    // 키보드 열렸을 때
                    if (!isKeyboardShowing) {
                        isKeyboardShowing = true

                        view?.setPadding(0, 0, 0, keypadHeight)
                    }
                } else {
                    // 키보드가 닫혔을 때
                    if (isKeyboardShowing) {
                        isKeyboardShowing = false
                        view?.setPadding(0, 0, 0, 0)
                    }
                }
            }
        }
    }

    companion object {
        private var instance: LabelCreateFragment? = null
        fun getInstance(): LabelCreateFragment {
            return instance ?: LabelCreateFragment().also { instance = it }
        }
    }
}