package com.nexters.fullstack

import android.app.Application
import com.nexters.fullstack.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(
                listOf(
                    viewModelModule
                )
            )
        }
        super.onCreate()
    }
}