package com.nexters.fullstack.data.mapper

import com.nexters.fullstack.data.db.entity.UserLabel
import com.nexters.fullstack.domain.Mapper
import com.nexters.fullstack.domain.entity.DomainUserLabel

object LocalLabelMapper : Mapper<DomainUserLabel, UserLabel> {
    override fun toData(data: DomainUserLabel): UserLabel {
        return UserLabel(color = data.color ?: "", text = data.text)
    }

    override fun fromData(data: UserLabel): DomainUserLabel {
        return DomainUserLabel(data.color, data.text)
    }
}