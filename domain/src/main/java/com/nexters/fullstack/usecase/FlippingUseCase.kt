package com.nexters.fullstack.usecase

import com.nexters.fullstack.source.LabellingState
import com.nexters.fullstack.usecase.base.BaseUseCase

internal class FlippingUseCase : BaseUseCase<LabellingState, Boolean> {
    override fun buildUseCase(params: LabellingState): Boolean {
        return when (params) {
            is LabellingState.Approve -> true

            is LabellingState.Rejected -> false

            else -> false
        }
    }
}
