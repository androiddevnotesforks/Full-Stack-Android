package com.nexters.fullstack.data.mapper

import com.nexters.fullstack.data.db.entity.UserLabelingImage
import com.nexters.fullstack.data.mapper.local.LocalLabelMapper
import com.nexters.fullstack.domain.source.local.DomainUserImage

object UserLabelingImageMapper : Mapper<DomainUserImage, UserLabelingImage> {
    override fun toDomain(data: DomainUserImage): UserLabelingImage {
        val labelMapper = data.labels.map(LocalLabelMapper::toDomain)

        val imageMapper = LocalImageMapper.toDomain(data.image)

        return UserLabelingImage(id = imageMapper.id, label = labelMapper, image = imageMapper)
    }

    override fun fromDomain(data: UserLabelingImage): DomainUserImage {

        val labelMapper = data.label.map(LocalLabelMapper::fromDomain)

        val imageMapper = LocalImageMapper.fromDomain(data.image)

        return DomainUserImage(labelMapper, imageMapper)
    }

}