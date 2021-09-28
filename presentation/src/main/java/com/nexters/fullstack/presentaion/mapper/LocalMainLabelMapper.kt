package com.nexters.fullstack.presentaion.mapper

import com.nexters.fullstack.domain.Mapper
import com.nexters.fullstack.domain.entity.DomainUserLabel
import com.nexters.fullstack.presentaion.model.LabelSource


object LocalMainLabelMapper : Mapper<LabelSource, DomainUserLabel> {
    override fun fromData(data: DomainUserLabel): LabelSource {
        return LabelSource(color = data.color ?: "", name = data.text)
    }

    override fun toData(data: LabelSource): DomainUserLabel {
        return DomainUserLabel(data.color, data.name)
    }
}
