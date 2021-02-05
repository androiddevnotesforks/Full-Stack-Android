package com.nexters.fullstack.repository

import com.nexters.fullstack.source.data.LocalImageDomain

interface AlbumRepository {
    fun getUnLabeling(pathFilter: String): List<LocalImageDomain>?
}