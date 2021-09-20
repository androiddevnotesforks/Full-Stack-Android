package com.nexters.fullstack.data.repository

import com.nexters.fullstack.data.local.image.LocalImages
import com.nexters.fullstack.data.mapper.LocalImageMapper
import com.nexters.fullstack.domain.repository.AlbumRepository
import com.nexters.fullstack.data.source.LocalImageData
import com.nexters.fullstack.domain.source.data.LocalImageDomain


internal class AlbumRepositoryImpl(private val localImages: LocalImages) : AlbumRepository {

    override fun getUnLabeling(pathFilter: String): List<LocalImageDomain> {
        return localImages.fetch(SCREENSHOT_POSTFIX).map { filePath ->
            LocalImageMapper.fromDomain(
                LocalImageData(id = filePath, originUrl = filePath)
            )
        }
    }

    companion object {
        private const val SCREENSHOT_POSTFIX = "Download"
    }
}