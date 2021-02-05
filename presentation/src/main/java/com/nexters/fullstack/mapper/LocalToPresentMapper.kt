package com.nexters.fullstack.mapper

import com.nexters.fullstack.source.PresentLocalFile
import com.nexters.fullstack.source.data.LocalImageDomain

class LocalToPresentMapper : Mapper<LocalImageDomain, PresentLocalFile> {
    override fun toData(data: LocalImageDomain): PresentLocalFile {
        return PresentLocalFile(data.originUrl)
    }

    override fun fromData(data: PresentLocalFile): LocalImageDomain {
        return LocalImageDomain(originUrl = data.url, id = data.url.length.toLong())
    }
}