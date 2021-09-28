package com.nexters.fullstack.domain.usecase

import com.nexters.fullstack.domain.repository.AlbumRepository
import com.nexters.fullstack.domain.repository.ImageRepository
import com.nexters.fullstack.domain.entity.LocalImageDomain
import com.nexters.fullstack.domain.usecase.base.BaseUseCase
import io.reactivex.Single

internal class AlbumLoadUseCase(
    private val albumRepository: AlbumRepository,
    private val imageRepository: ImageRepository
) :
    BaseUseCase<String, Single<List<LocalImageDomain>?>> {
    override fun buildUseCase(params: String): Single<List<LocalImageDomain>?> {
        val images = albumRepository.getUnLabeling(params)
        return imageRepository.load()
            .map { dbImages ->
                images.filter { localImage ->
                    dbImages.find {
                        it.labels.isNotEmpty() && it.image.id == localImage.id
                    } == null
                }
            }.toSingle(emptyList())
    }
}