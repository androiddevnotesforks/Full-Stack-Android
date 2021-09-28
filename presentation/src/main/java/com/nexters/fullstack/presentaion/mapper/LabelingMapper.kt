package com.nexters.fullstack.presentaion.mapper

import com.nexters.fullstack.domain.Mapper
import com.nexters.fullstack.presentaion.model.MainMakeLabelSource
import com.nexters.fullstack.domain.entity.DomainUserLabel

object LabelingMapper : Mapper<DomainUserLabel, MainMakeLabelSource> {
    override fun toData(data: DomainUserLabel): MainMakeLabelSource {
        return MainMakeLabelSource(data.text)
    }

    override fun fromData(data: MainMakeLabelSource): DomainUserLabel {
        return DomainUserLabel(data.palletItem?.name, data.labelText)
    }
}