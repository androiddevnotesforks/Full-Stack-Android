package com.nexters.fullstack.domain.usecase

import com.nexters.fullstack.domain.source.LabellingState
import com.nexters.fullstack.domain.usecase.base.BaseUseCase

internal class FlippingUseCase : BaseUseCase<LabellingState, Boolean> {
    override fun buildUseCase(params: LabellingState): Boolean {
        return when (params) {
            is LabellingState.Approve -> true

            is LabellingState.Rejected -> false

            else -> false
        }
    }
}
