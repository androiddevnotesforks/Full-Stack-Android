package com.nexters.fullstack.mapper

import com.nexters.fullstack.source.PresentLocalFile
import com.nexters.fullstack.source.data.LocalImageDomain

object PresenterLocalFileMapper : Mapper<PresentLocalFile, LocalImageDomain> {
    override fun toData(data: PresentLocalFile): LocalImageDomain {
        return LocalImageDomain(originUrl = data.url)
    }

    override fun fromData(data: LocalImageDomain): PresentLocalFile {
        return PresentLocalFile(data.originUrl)
    }

}