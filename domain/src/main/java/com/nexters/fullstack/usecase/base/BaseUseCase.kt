package com.nexters.fullstack.usecase.base

abstract class BaseUseCase<Params, Result> {
    abstract fun buildUseCase(params: Params): Result?

    operator fun invoke(params: Params) = buildUseCase(params)
}