package com.nexters.fullstack.presentaion.mapper

import com.nexters.fullstack.presentaion.source.LabelSource
import com.nexters.fullstack.domain.source.local.DomainUserLabel

object LocalMainLabelMapper : Mapper<LabelSource, DomainUserLabel> {
    override fun toData(item: DomainUserLabel): LabelSource {
        return LabelSource(color = item.color ?: "", name = item.text)
    }

    override fun fromData(item: LabelSource): DomainUserLabel {
        return DomainUserLabel(item.color, item.name)
    }
}