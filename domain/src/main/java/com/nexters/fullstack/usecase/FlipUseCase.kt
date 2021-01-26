package com.nexters.fullstack.usecase

import com.nexters.fullstack.source.LabellingState

class FlipUseCase : BaseFlipUseCase<LabellingState>() {
    override fun buildUseCase(params: LabellingState): Boolean {
        return when (params) {
            is LabellingState.Approve -> true

            is LabellingState.Rejected -> false

            else -> false
        }
    }
}
