package com.nexters.fullstack.di

import com.nexters.fullstack.source.LabellingState
import com.nexters.fullstack.usecase.BaseFlipUseCase
import com.nexters.fullstack.usecase.FlipUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<BaseFlipUseCase<LabellingState>> { FlipUseCase() }
}