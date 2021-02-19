package com.nexters.fullstack.mapper

import com.nexters.fullstack.source.LabelSource
import com.nexters.fullstack.source.local.DomainUserLabel

object LocalMainLabelMapper : Mapper<LabelSource, DomainUserLabel> {
    override fun toData(item: DomainUserLabel): LabelSource {
        return LabelSource(color = item.color ?: "", name = item.text)
    }

    override fun fromData(item: LabelSource): DomainUserLabel {
        return DomainUserLabel(item.color, item.name)
    }
}