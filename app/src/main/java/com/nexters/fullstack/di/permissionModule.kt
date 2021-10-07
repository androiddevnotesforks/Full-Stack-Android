package com.nexters.fullstack.domain.di

import com.nexters.fullstack.helper.PermissionHelper
import com.nexters.fullstack.helper.PermissionHelperImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val permissionModule = module {
    factory<PermissionHelper> { PermissionHelperImpl(androidContext()) }
}