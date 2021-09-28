package com.nexters.fullstack.data.mapper

import com.nexters.fullstack.data.db.entity.UserLabelingImage
import com.nexters.fullstack.domain.Mapper
import com.nexters.fullstack.domain.entity.DomainUserImage

object UserLabelingImageMapper : Mapper<UserLabelingImage, DomainUserImage> {
    override fun fromData(data: DomainUserImage): UserLabelingImage {
        val labelMapper = data.labels.map(LocalLabelMapper::toData)

        val imageMapper = LocalImageMapper.toData(data.image)

        return UserLabelingImage(id = imageMapper.id, label = labelMapper, image = imageMapper)
    }

    override fun toData(data: UserLabelingImage): DomainUserImage {

        val labelMapper = data.label.map(LocalLabelMapper::fromData)

        val imageMapper = LocalImageMapper.fromData(data.image)

        return DomainUserImage(labelMapper, imageMapper)
    }
}