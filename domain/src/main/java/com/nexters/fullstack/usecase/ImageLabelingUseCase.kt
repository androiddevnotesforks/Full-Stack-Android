package com.nexters.fullstack.usecase

import com.nexters.fullstack.repository.ImageRepository
import com.nexters.fullstack.source.data.DomainLabelSource
import com.nexters.fullstack.source.data.LocalFileDomain
import com.nexters.fullstack.source.local.DomainUserImage
import com.nexters.fullstack.usecase.base.BaseUseCase
import io.reactivex.Completable

class ImageLabelingUseCase(private val imageRepository: ImageRepository) :
    BaseUseCase<DomainUserImage, Completable> {
    override fun buildUseCase(params: DomainUserImage): Completable {
        return imageRepository.save(params)
    }
}