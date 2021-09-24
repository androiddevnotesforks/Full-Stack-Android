package com.nexters.fullstack.presentaion.mapper

import com.nexters.fullstack.presentaion.source.LabelSource
import com.nexters.fullstack.domain.source.local.DomainUserLabel

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