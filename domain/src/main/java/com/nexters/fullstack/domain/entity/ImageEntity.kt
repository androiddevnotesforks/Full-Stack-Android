package com.nexters.fullstack.domain.entity

data class ImageEntity(
    val labels: List<LabelEntity>,
    val image: FileImageEntity,
    val liked: Boolean = false
) {
    companion object {
        fun empty(): ImageEntity {
            return ImageEntity(emptyList(), FileImageEntity.empty(), false)
        }
    }
}
