package com.nexters.fullstack.mapper

import com.nexters.fullstack.source.LabelSource
import com.nexters.fullstack.source.data.DomainLabelSource
import com.nexters.fullstack.source.local.DomainUserLabel

object LabelSourceMapper : Mapper<List<LabelSource>, List<DomainUserLabel>> {
    override fun toData(data: List<LabelSource>): List<DomainUserLabel> {
        return data.map {
            DomainUserLabel(color = it.color, text = it.name)
        }
    }

    override fun fromData(data: List<DomainUserLabel>): List<LabelSource> {
        return data.map {
            LabelSource(color = it.color ?: "", name = it.text)
        }
    }
}