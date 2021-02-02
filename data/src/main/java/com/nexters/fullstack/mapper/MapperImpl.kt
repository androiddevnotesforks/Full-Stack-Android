package com.nexters.fullstack.mapper

import com.nexters.fullstack.source.LocalLabelData
import com.nexters.fullstack.source.data.LocalLabelDomain

object MapperImpl : Mapper<LocalLabelDomain, LocalLabelData> {
    override fun toDomain(data: LocalLabelDomain): LocalLabelData {
        return LocalLabelData(data.id, data.originUrl)
    }

    override fun fromDomain(data: LocalLabelData): LocalLabelDomain {
        return LocalLabelDomain(data.id, data.originUrl)
    }
}