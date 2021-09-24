package com.nexters.fullstack.presentaion.mapper

import com.nexters.fullstack.presentaion.source.PresentLocalFile
import com.nexters.fullstack.domain.source.data.LocalImageDomain

object PresenterLocalFileMapper : Mapper<PresentLocalFile, LocalImageDomain> {
    override fun toData(data: PresentLocalFile): LocalImageDomain {
        return LocalImageDomain(id = data.url, originUrl = data.url)
    }

    override fun fromData(data: LocalImageDomain): PresentLocalFile {
        return PresentLocalFile(data.originUrl)
    }

}