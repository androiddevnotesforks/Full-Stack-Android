package com.nexters.fullstack.di

import com.nexters.fullstack.local.image.LocalImages
import com.nexters.fullstack.local.image.LocalImagesImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localImageModule = module {
    factory<LocalImages> {
        LocalImagesImpl(androidContext())
    }
}