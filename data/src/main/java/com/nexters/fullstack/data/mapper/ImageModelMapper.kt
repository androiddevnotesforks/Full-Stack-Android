package com.nexters.fullstack.data.mapper

import com.nexters.fullstack.data.db.entity.ImageModel
import com.nexters.fullstack.data.db.entity.ImageWithLabels
import com.nexters.fullstack.domain.Mapper
import com.nexters.fullstack.domain.entity.ImageEntity

object ImageModelMapper : Mapper<ImageWithLabels, ImageEntity> {
    override fun fromData(data: ImageEntity): ImageWithLabels {
        val labelMapper = data.labels.map(LabelModelMapper::toData)
        val imageMapper = FileImageMapper.toData(data.image)

        return ImageWithLabels(
            image = ImageModel(imageMapper.id,image = imageMapper, liked = data.liked),
            labels = labelMapper
        )
    }

    override fun toData(data: ImageWithLabels): ImageEntity {
        val labelMapper = data.labels.map(LabelModelMapper::fromData)
        val imageMapper = FileImageMapper.fromData(data.image.image)

        return ImageEntity(labelMapper, imageMapper, data.image.liked)
    }
}