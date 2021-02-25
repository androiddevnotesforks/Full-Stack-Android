package com.nexters.feature.di

import com.nexters.feature.LocalImages
import com.nexters.feature.LocalImagesImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localImageModule = module {
    factory<LocalImages> { LocalImagesImpl(androidContext()) }
}