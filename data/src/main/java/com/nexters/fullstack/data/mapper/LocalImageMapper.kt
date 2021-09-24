package com.nexters.fullstack.data.mapper

import com.nexters.fullstack.data.source.LocalImageData
import com.nexters.fullstack.domain.source.data.LocalImageDomain

object LocalImageMapper : Mapper<LocalImageDomain, LocalImageData> {
    override fun toDomain(data: LocalImageDomain): LocalImageData {
        return LocalImageData(data.id, data.originUrl)
    }

    override fun fromDomain(data: LocalImageData): LocalImageDomain {
        return LocalImageDomain(data.id, data.originUrl)
    }
}