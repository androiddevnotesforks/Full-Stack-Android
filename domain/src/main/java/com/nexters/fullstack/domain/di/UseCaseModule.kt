package com.nexters.fullstack.domain.di

import com.nexters.fullstack.domain.usecase.*
import com.nexters.fullstack.domain.usecase.GetUnlabeledImages
import org.koin.dsl.module

val useCaseModule = module {
    single {
        GetUnlabeledImages(
            get(),
            get()
        )
    }
    single { LabelingUseCase(get()) }

    single { CreateLabel(get()) }

    single { GetLabels(get()) }

    single { RequestLabeling(get(), get()) }

    single {
        LoadImageUseCase(
            get()
        )
    }
}