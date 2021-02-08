package com.nexters.fullstack.repository

import com.nexters.fullstack.mapper.LocalImageMapper
import com.nexters.fullstack.source.LocalImageData
import com.nexters.fullstack.source.data.LocalImageDomain
import com.tsdev.feature.LocalImages


internal class AlbumRepositoryImpl(private val localImages: LocalImages) : AlbumRepository {

    override fun getUnLabeling(pathFilter: String): List<LocalImageDomain> {
        return localImages.fetch(SCREENSHOT_POSTFIX).map { filePath ->
            LocalImageMapper.fromDomain(
                LocalImageData(originUrl = filePath)
            )
        }
    }

    companion object {
        private const val SCREENSHOT_POSTFIX = "Screenshots"
    }
}