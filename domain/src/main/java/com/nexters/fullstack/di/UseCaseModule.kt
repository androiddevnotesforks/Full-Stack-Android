package com.nexters.fullstack.di

import com.nexters.fullstack.source.LabellingState
import com.nexters.fullstack.source.data.LocalLabelDomain
import com.nexters.fullstack.usecase.AlbumLoadUseCase
import com.nexters.fullstack.usecase.FlippingUseCase
import com.nexters.fullstack.usecase.LabelingUseCase
import com.nexters.fullstack.usecase.base.BaseUseCase
import io.reactivex.Single
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCaseModule = module {
    single<BaseUseCase<LabellingState, Boolean>> { FlippingUseCase() }

    single<BaseUseCase<String, Single<List<LocalLabelDomain>?>>>(named("label")) { AlbumLoadUseCase(get()) }
    single { LabelingUseCase() }
}