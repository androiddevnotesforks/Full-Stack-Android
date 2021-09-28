package com.nexters.fullstack.domain.usecase

import com.nexters.fullstack.domain.repository.ImageRepository
import com.nexters.fullstack.domain.entity.DomainUserImage
import com.nexters.fullstack.domain.usecase.base.BaseUseCase
import io.reactivex.Maybe

class LoadImageUseCase(private val labelaryRepository: ImageRepository) :
    BaseUseCase<Unit, Maybe<List<DomainUserImage>>> {
    override fun buildUseCase(params: Unit): Maybe<List<DomainUserImage>> {
        return labelaryRepository.load()
    }
}