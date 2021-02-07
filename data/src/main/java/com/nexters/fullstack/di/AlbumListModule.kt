package com.nexters.fullstack.di

import com.nexters.fullstack.repository.AlbumRepository
import com.nexters.fullstack.repository.AlbumRepositoryImpl
import org.koin.dsl.module

val albumListModule = module {
    factory<AlbumRepository> { AlbumRepositoryImpl(get()) }
}