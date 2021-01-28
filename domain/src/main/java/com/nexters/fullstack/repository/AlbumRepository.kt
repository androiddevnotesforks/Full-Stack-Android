package com.nexters.fullstack.repository

import com.nexters.fullstack.source.data.LocalLabelDomain

interface AlbumRepository {
    fun getUnLabeling(): List<LocalLabelDomain>?
}