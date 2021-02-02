package com.nexters.fullstack.repository

import com.nexters.fullstack.mapper.MapperImpl
import com.nexters.fullstack.source.LocalLabelData
import com.nexters.fullstack.source.data.LocalLabelDomain
import com.tsdev.feature.LocalImages


internal class AlbumRepositoryImpl(private val localImages: LocalImages) : AlbumRepository {

    override fun getUnLabeling(pathFilter: String): List<LocalLabelDomain> {
        return localImages.fetch(SCREENSHOT_POSTFIX).map { filePath ->
            MapperImpl.fromDomain(
                LocalLabelData(originUrl = filePath)
            )
        }
    }

    companion object {
        private const val SCREENSHOT_POSTFIX = "Screenshots"
    }
}