package com.nexters.fullstack.usecase

import com.nexters.fullstack.repository.ImageRepository
import com.nexters.fullstack.source.local.DomainUserImage
import com.nexters.fullstack.usecase.base.BaseUseCase
import io.reactivex.Maybe

class LoadImageUseCase(private val labelaryRepository: ImageRepository) : BaseUseCase<Unit, Maybe<List<DomainUserImage>>>{
    override fun buildUseCase(params: Unit): Maybe<List<DomainUserImage>> {
        return labelaryRepository.load()
    }
}