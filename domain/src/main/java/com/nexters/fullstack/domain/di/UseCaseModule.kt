package com.nexters.fullstack.domain.di

import com.nexters.fullstack.domain.entity.ImageEntity
import com.nexters.fullstack.domain.usecase.*
import com.nexters.fullstack.domain.usecase.GetUnlabeledImages
import com.nexters.fullstack.domain.usecase.base.BaseUseCase
import io.reactivex.Completable
import io.reactivex.Single
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCaseModule = module {
    single {
        GetUnlabeledImages(
            get(),
            get()
        )
    }

    single { CreateLabel(get()) }

    single { GetLabels(get()) }

    single { RequestLabeling(get()) }

    single { RejctLabeling(get()) }

    single {
        LoadImageUseCase(
            get()
        )
    }

    single { SearchImagesByLabel(get()) }

    single { SearchImagesBySingleLabel(get()) }
    single { PostBookmarkingImageUseCase(get()) }
    single { DeleteImageUseCase(get()) }
    single { GetDetailImage(get(), get()) }
}