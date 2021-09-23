package com.nexters.fullstack.domain.repository

import com.nexters.fullstack.domain.source.data.LocalImageDomain

interface AlbumRepository {
    fun getUnLabeling(pathFilter: String): List<LocalImageDomain>?
}