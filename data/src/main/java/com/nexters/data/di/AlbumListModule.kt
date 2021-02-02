package com.nexters.data.di

import com.nexters.fullstack.repository.AlbumRepository
import com.nexters.data.repository.AlbumRepositoryImpl
import org.koin.dsl.module

val albumListModule = module {
    factory<AlbumRepository> { AlbumRepositoryImpl(get()) }
}