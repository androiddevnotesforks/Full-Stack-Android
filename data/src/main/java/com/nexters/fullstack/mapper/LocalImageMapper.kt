package com.nexters.fullstack.mapper

import com.nexters.fullstack.source.LocalImageData
import com.nexters.fullstack.source.data.LocalImageDomain

object LocalImageMapper : Mapper<LocalImageDomain, LocalImageData> {
    override fun toDomain(data: LocalImageDomain): LocalImageData {
        return LocalImageData(data.id, data.originUrl)
    }

    override fun fromDomain(data: LocalImageData): LocalImageDomain {
        return LocalImageDomain(data.id, data.originUrl)
    }
}