package com.nexters.fullstack.data.repository

import com.nexters.fullstack.data.local.filesystem.FileSystemImages
import com.nexters.fullstack.data.mapper.LocalImageMapper
import com.nexters.fullstack.domain.repository.AlbumRepository
import com.nexters.fullstack.data.model.LocalImageData
import com.nexters.fullstack.domain.entity.LocalImageDomain


internal class AlbumRepositoryImpl(private val fileSystemImages: FileSystemImages) : AlbumRepository {

    override fun getUnLabeling(pathFilter: String): List<LocalImageDomain> {
        return fileSystemImages.fetch(DOWNLOAD_POSTFIX).map { filePath ->
            LocalImageMapper.fromData(
                LocalImageData(id = filePath, originUrl = filePath)
            )
        }
    }

    companion object {
        private const val SCREENSHOT_POSTFIX = "Screenshot"
        private const val DOWNLOAD_POSTFIX = "Download"
    }
}