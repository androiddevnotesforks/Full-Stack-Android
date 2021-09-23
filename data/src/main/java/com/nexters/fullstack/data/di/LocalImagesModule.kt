package com.nexters.fullstack.data.di

import com.nexters.fullstack.data.local.image.LocalImages
import com.nexters.fullstack.data.local.image.LocalImagesImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localImageModule = module {
    factory<LocalImages> {
        LocalImagesImpl(androidContext())
    }
}