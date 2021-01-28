package com.nexters.data.mapper

import com.nexters.data.source.LocalLabelData
import com.nexters.fullstack.source.data.LocalLabelDomain

object MapperImpl : Mapper<LocalLabelDomain, LocalLabelData> {
    override fun toDomain(data: LocalLabelDomain): LocalLabelData {
        return LocalLabelData(data.id, data.originUrl)
    }

    override fun fromDomain(data: LocalLabelData): LocalLabelDomain {
        return LocalLabelDomain(data.id, data.originUrl)
    }
}