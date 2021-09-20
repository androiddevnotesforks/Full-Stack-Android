package com.nexters.fullstack.presentaion.mapper

import com.nexters.fullstack.presentaion.source.PresentLocalFile
import com.nexters.fullstack.domain.source.data.LocalImageDomain

class LocalToPresentMapper : Mapper<LocalImageDomain, PresentLocalFile> {
    override fun toData(data: LocalImageDomain): PresentLocalFile {
        return PresentLocalFile(data.originUrl)
    }

    override fun fromData(data: PresentLocalFile): LocalImageDomain {
        return LocalImageDomain( id = data.url, originUrl = data.url)
    }
}