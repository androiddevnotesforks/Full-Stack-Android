package com.nexters.fullstack.data.mapper

import com.nexters.fullstack.data.model.FileImage
import com.nexters.fullstack.domain.Mapper
import com.nexters.fullstack.domain.entity.FileImageEntity

object FileImageMapper : Mapper<FileImageEntity, FileImage> {
    override fun toData(data: FileImageEntity): FileImage {
        return FileImage(data.id, data.originUrl)
    }

    override fun fromData(data: FileImage): FileImageEntity {
        return FileImageEntity(data.id, data.originUrl)
    }
}