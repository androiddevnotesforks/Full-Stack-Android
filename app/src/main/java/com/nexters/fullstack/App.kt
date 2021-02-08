package com.nexters.fullstack

import android.app.Application
import com.nexters.fullstack.di.*
import com.tsdev.feature.di.localImageModule
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    companion object{
        lateinit var app: App
    }
    override fun onCreate() {
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(
                listOf(
                    useCaseModule,
                    presentationMapper,
                    viewModelModule,
                    permissionModule,
                    albumListModule,
                    localImageModule,
                    databaseModule,
                    localDataSourceModule
                )
            )
        }

        RxJavaPlugins.setErrorHandler {
            it.printStackTrace()
        }
        super.onCreate()
        app = this
    }
}