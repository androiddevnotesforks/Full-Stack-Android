package com.nexters.fullstack.mapper

import com.nexters.fullstack.source.PresentLocalFile
import com.nexters.fullstack.source.data.LocalLabelDomain

class LocalToPresentMapper : Mapper<LocalLabelDomain, PresentLocalFile> {
    override fun toData(data: LocalLabelDomain): PresentLocalFile {
        return PresentLocalFile(data.originUrl)
    }

    override fun fromData(data: PresentLocalFile): LocalLabelDomain {
        return LocalLabelDomain(originUrl = data.url, id = data.url.length.toLong())
    }
}