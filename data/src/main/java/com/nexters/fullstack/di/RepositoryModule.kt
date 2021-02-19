package com.nexters.fullstack.di

import com.nexters.fullstack.repository.*
import com.nexters.fullstack.repository.AlbumRepositoryImpl
import org.koin.dsl.module

val albumListModule = module {
    factory<AlbumRepository> { AlbumRepositoryImpl(get()) }

    factory<LabelRepository> { LabelRepositoryImpl(get()) }

    factory<ImageRepository> { ImageRepositoryImpl(get()) }
}