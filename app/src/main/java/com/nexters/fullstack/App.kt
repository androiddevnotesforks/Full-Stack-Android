package com.nexters.fullstack

import android.app.Application
import com.nexters.fullstack.di.viewModelModule
import com.nexters.fullstack.di.permissionModule
import com.nexters.fullstack.di.useCaseModule
import io.reactivex.android.plugins.RxAndroidPlugins
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
                    viewModelModule,
                    permissionModule
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