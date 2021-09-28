package com.nexters.fullstack.presentaion.mapper

import com.nexters.fullstack.domain.Mapper
import com.nexters.fullstack.presentaion.model.PresentLocalFile
import com.nexters.fullstack.domain.entity.LocalImageDomain

object LocalToPresentMapper : Mapper<LocalImageDomain, PresentLocalFile> {
    override fun toData(data: LocalImageDomain): PresentLocalFile {
        return PresentLocalFile(data.originUrl)
    }

    override fun fromData(data: PresentLocalFile): LocalImageDomain {
        return LocalImageDomain( id = data.url, originUrl = data.url)
    }
}