package com.nexters.fullstack.domain.repository

import com.nexters.fullstack.domain.entity.FileImageEntity

interface AlbumRepository {
    fun getUnLabeling(pathFilter: String): List<FileImageEntity>
}