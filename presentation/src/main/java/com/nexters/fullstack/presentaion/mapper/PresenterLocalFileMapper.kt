package com.nexters.fullstack.presentaion.mapper

import com.nexters.fullstack.domain.Mapper
import com.nexters.fullstack.presentaion.model.PresentLocalFile
import com.nexters.fullstack.domain.entity.LocalImageDomain

object PresenterLocalFileMapper : Mapper<PresentLocalFile, LocalImageDomain> {
    override fun toData(data: PresentLocalFile): LocalImageDomain {
        return LocalImageDomain(id = data.url, originUrl = data.url)
    }

    override fun fromData(data: LocalImageDomain): PresentLocalFile {
        return PresentLocalFile(data.originUrl)
    }

}