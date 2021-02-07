package com.tsdev.feature.di

import com.tsdev.feature.LocalImages
import com.tsdev.feature.LocalImagesImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localImageModule = module {
    factory<LocalImages> { LocalImagesImpl(androidContext()) }
}