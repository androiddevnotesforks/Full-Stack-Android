package com.nexters.fullstack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.source.LabellingState
import com.nexters.fullstack.source.PresentLocalFile
import com.nexters.fullstack.usecase.BaseFlipUseCase
import com.nexters.fullstack.usecase.FlipUseCase

class MainViewModel(private val flipUseCase: BaseFlipUseCase<LabellingState>) : BaseViewModel() {

    private val _labellingState = MutableLiveData<LabellingState>(LabellingState.Pending)

    val labellingState: LiveData<LabellingState>
        get() = _labellingState

    private val _screenItems = MutableLiveData<List<PresentLocalFile>>(
        listOf(
            PresentLocalFile("https://source.unsplash.com/Xq1ntWruZQI/600x800"),

            PresentLocalFile("https://source.unsplash.com/NYyCqdBOKwc/600x800"),

            PresentLocalFile("https://source.unsplash.com/buF62ewDLcQ/600x800"),

            PresentLocalFile("https://source.unsplash.com/THozNzxEP3g/600x800"),

            PresentLocalFile("https://source.unsplash.com/USrZRcRS2Lw/600x800"),

            PresentLocalFile("https://source.unsplash.com/PeFk7fzxTdk/600x800"),

            PresentLocalFile("https://source.unsplash.com/LrMWHKqilUw/600x800"),

            PresentLocalFile("https://source.unsplash.com/HN-5Z6AmxrM/600x800"),
            PresentLocalFile("https://source.unsplash.com/CdVAUADdqEc/600x800"),

            PresentLocalFile("https://source.unsplash.com/AWh9C-QjhE4/600x800")
        )
    )

    val screenItems: LiveData<List<PresentLocalFile>>
        get() = _screenItems


    fun setButtonState(labelState: LabellingState) {
        _labellingState.value = labelState
    }

    fun isLabellingStart(labelState: LabellingState): Boolean {
        return flipUseCase(labelState)
    }

}