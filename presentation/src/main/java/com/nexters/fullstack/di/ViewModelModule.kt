package com.nexters.fullstack.di

import com.nexters.fullstack.viewmodel.LabelOutAppViewModel
import com.nexters.fullstack.viewmodel.LabelingViewModel
import com.nexters.fullstack.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LabelOutAppViewModel() }
    viewModel { MainViewModel() }
    viewModel { LabelingViewModel() }
}