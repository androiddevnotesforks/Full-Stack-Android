package com.nexters.fullstack.presentaion.mapper

import com.nexters.fullstack.domain.Mapper
import com.nexters.fullstack.presentaion.model.MainMakeLabelSource
import com.nexters.fullstack.domain.entity.LabelEntity

object LabelingMapper : Mapper<LabelEntity, MainMakeLabelSource> {
    override fun toData(data: LabelEntity): MainMakeLabelSource {
        return MainMakeLabelSource(data.text)
    }

    override fun fromData(data: MainMakeLabelSource): LabelEntity {
        return LabelEntity(-1L, data.palletItem?.name, data.labelText)
    }
}