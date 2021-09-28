package com.nexters.fullstack.data.mapper

import com.nexters.fullstack.data.model.LocalImageData
import com.nexters.fullstack.domain.Mapper
import com.nexters.fullstack.domain.entity.LocalImageDomain

object LocalImageMapper : Mapper<LocalImageDomain, LocalImageData> {
    override fun toData(data: LocalImageDomain): LocalImageData {
        return LocalImageData(data.id, data.originUrl)
    }

    override fun fromData(data: LocalImageData): LocalImageDomain {
        return LocalImageDomain(data.id, data.originUrl)
    }
}