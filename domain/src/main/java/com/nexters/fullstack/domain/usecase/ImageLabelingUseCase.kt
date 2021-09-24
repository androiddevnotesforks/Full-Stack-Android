package com.nexters.fullstack.domain.usecase

import com.nexters.fullstack.domain.repository.ImageRepository
import com.nexters.fullstack.domain.source.local.DomainUserImage
import com.nexters.fullstack.domain.usecase.base.BaseUseCase
import io.reactivex.Completable

class ImageLabelingUseCase(private val imageRepository: ImageRepository) :
    BaseUseCase<DomainUserImage, Completable> {
    override fun buildUseCase(params: DomainUserImage): Completable {
        return imageRepository.save(params)
    }
}