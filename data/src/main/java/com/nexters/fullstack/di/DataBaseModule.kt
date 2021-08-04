package com.nexters.fullstack.di

import com.nexters.fullstack.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { AppDatabase.getInstance(androidContext()) }

    single { get<AppDatabase>().labelDAO() }

    single { get<AppDatabase>().imageDAO() }

    single { get<AppDatabase>().favoriteDAO() }
}