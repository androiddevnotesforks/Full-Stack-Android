package com.nexters.fullstack.domain.usecase

import com.nexters.fullstack.domain.repository.ImageRepository
import com.nexters.fullstack.domain.entity.LocalImageDomain
import com.nexters.fullstack.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow

class SearchScreenshotUseCase(private val ImageRepository : ImageRepository) :
    BaseUseCase<Unit, Flow<List<LocalImageDomain>>> {
    override fun buildUseCase(params : Unit): Flow<List<LocalImageDomain>> {
        TODO("Not yet implemented")
    }
}