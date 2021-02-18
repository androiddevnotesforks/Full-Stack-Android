package com.nexters.fullstack.di

import com.nexters.fullstack.local.LabelaryLocalDataSource
import com.nexters.fullstack.local.LabelaryLocalDataSourceImpl
import org.koin.dsl.module

val localDataSourceModule = module {
    single<LabelaryLocalDataSource.Label> { LabelaryLocalDataSourceImpl(get()) }

    single<LabelaryLocalDataSource.Image> { LabelaryLocalDataSourceImpl(get()) }
}