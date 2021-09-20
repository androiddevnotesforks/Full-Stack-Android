package com.nexters.fullstack.domain.di

import com.nexters.fullstack.domain.constants.KoinNamed
import com.nexters.fullstack.domain.source.LabellingState
import com.nexters.fullstack.domain.source.data.LocalImageDomain
import com.nexters.fullstack.domain.source.local.DomainUserImage
import com.nexters.fullstack.domain.usecase.*
import com.nexters.fullstack.domain.usecase.AlbumLoadUseCase
import com.nexters.fullstack.domain.usecase.FlippingUseCase
import com.nexters.fullstack.domain.usecase.base.BaseUseCase
import io.reactivex.Maybe
import io.reactivex.Single
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCaseModule = module {
    single<BaseUseCase<LabellingState, Boolean>> { FlippingUseCase() }

    single<BaseUseCase<String, Single<List<LocalImageDomain>?>>>(named(KoinNamed.LABEL)) {
        AlbumLoadUseCase(
            get(),
            get()
        )
    }
    single { LabelingUseCase(get()) }

    single { GetLabelManagementUseCase(get()) }

    single { ImageLabelingUseCase(get()) }

    single<BaseUseCase<Unit, Maybe<List<DomainUserImage>>>>(named(KoinNamed.IMAGE)) {
        LoadImageUseCase(
            get()
        )
    }
}