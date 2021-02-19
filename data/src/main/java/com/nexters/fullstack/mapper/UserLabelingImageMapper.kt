package com.nexters.fullstack.mapper

import com.nexters.fullstack.db.entity.UserLabelingImage
import com.nexters.fullstack.mapper.local.LocalLabelMapper
import com.nexters.fullstack.source.local.DomainUserImage

object UserLabelingImageMapper : Mapper<DomainUserImage, UserLabelingImage> {
    override fun toDomain(data: DomainUserImage): UserLabelingImage {
        val labelMapper = data.labels.map(LocalLabelMapper::toDomain)

        val imageMapper = LocalImageMapper.toDomain(data.image)

        return UserLabelingImage(labelMapper, imageMapper)
    }

    override fun fromDomain(data: UserLabelingImage): DomainUserImage {

        val labelMapper = data.label.map(LocalLabelMapper::fromDomain)

        val imageMapper = LocalImageMapper.fromDomain(data.image)

        return DomainUserImage(labelMapper, imageMapper)
    }

}