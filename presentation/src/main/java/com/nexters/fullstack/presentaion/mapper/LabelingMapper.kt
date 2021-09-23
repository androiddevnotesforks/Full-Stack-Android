package com.nexters.fullstack.presentaion.mapper

import com.nexters.fullstack.presentaion.source.MainMakeLabelSource
import com.nexters.fullstack.domain.source.local.DomainUserLabel

class LabelingMapper : Mapper<DomainUserLabel, MainMakeLabelSource> {
    override fun toData(data: DomainUserLabel): MainMakeLabelSource {
        return MainMakeLabelSource(data.text)
    }

    override fun fromData(data: MainMakeLabelSource): DomainUserLabel {
        return DomainUserLabel(data.palletItem?.name, data.labelText)
    }
}