package com.nexters.fullstack.usecase

import com.nexters.fullstack.repository.ImageRepository
import com.nexters.fullstack.source.data.DomainLabelSource
import com.nexters.fullstack.source.data.LocalFileDomain
import com.nexters.fullstack.usecase.base.BaseUseCase
import io.reactivex.Completable

class ImageLabelingUseCase(private val imageRepository: ImageRepository) :
    BaseUseCase<Pair<List<DomainLabelSource>, LocalFileDomain>, Completable> {
    override fun buildUseCase(params: Pair<List<DomainLabelSource>, LocalFileDomain>): Completable {
        return imageRepository.save()
    }
}