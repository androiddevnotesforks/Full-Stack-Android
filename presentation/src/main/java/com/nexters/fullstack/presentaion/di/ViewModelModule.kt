package com.nexters.fullstack.presentaion.di

import com.nexters.fullstack.domain.Mapper
import com.nexters.fullstack.domain.constants.KoinNamed
import com.nexters.fullstack.presentaion.model.PresentLocalFile
import com.nexters.fullstack.domain.entity.LocalImageDomain
import com.nexters.fullstack.presentaion.mapper.LocalToPresentMapper
import com.nexters.fullstack.presentaion.viewmodel.LabelOutAppViewModel
import com.nexters.fullstack.presentaion.viewmodel.LabelingViewModel
import com.nexters.fullstack.presentaion.viewmodel.MainViewModel
import com.nexters.fullstack.viewmodel.*
import com.nexters.fullstack.viewmodel.detail.DetailAlbumViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


val presentationMapper = module {
    single<Mapper<LocalImageDomain, PresentLocalFile>> { LocalToPresentMapper }
}

val viewModelModule = module {
    viewModel { LabelOutAppViewModel(get()) }
    viewModel { MainViewModel(get(), get(named("label")), get(named("image")), get()) }
    viewModel { OnBoardingViewModel() }
    viewModel { LabelingViewModel(get(), get(named(KoinNamed.IMAGE)), get()) }
    viewModel { BottomSheetViewModel() }
    viewModel { AlbumViewModel() }
    viewModel { HomeMainViewModel() }
    viewModel { HomeSearchViewModel() }
    viewModel { HomeScreenshotViewModel() }
    viewModel { ScreenshotDetailViewModel() }
    viewModel { HomeSearchResultViewModel() }
    viewModel { DetailAlbumViewModel() }
}
