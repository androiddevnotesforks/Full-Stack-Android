package com.nexters.fullstack.usecase.base

interface BaseUseCase<Params, Result> {
    fun buildUseCase(params: Params): Result
}