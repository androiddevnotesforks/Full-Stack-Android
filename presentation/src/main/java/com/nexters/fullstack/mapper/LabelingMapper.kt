package com.nexters.fullstack.mapper

import com.nexters.fullstack.source.MainMakeLabelSource
import com.nexters.fullstack.source.local.DomainUserLabel

class LabelingMapper : Mapper<DomainUserLabel, MainMakeLabelSource> {
    override fun toData(data: DomainUserLabel): MainMakeLabelSource {
        return MainMakeLabelSource(data.text)
    }

    override fun fromData(data: MainMakeLabelSource): DomainUserLabel {
        return DomainUserLabel(data.palletItem?.selectedBackgroundColor, data.labelText)
    }
}