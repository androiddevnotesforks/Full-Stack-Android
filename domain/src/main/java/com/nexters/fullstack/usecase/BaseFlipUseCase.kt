package com.nexters.fullstack.usecase

import com.nexters.fullstack.UseCase

abstract class BaseFlipUseCase<Params> : UseCase {
    abstract fun buildUseCase(params: Params): Boolean

    operator fun invoke(params: Params) = buildUseCase(params)
}