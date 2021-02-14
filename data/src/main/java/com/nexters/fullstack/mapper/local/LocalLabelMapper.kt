package com.nexters.fullstack.mapper.local

import com.nexters.fullstack.db.entity.UserLabel
import com.nexters.fullstack.mapper.Mapper
import com.nexters.fullstack.source.local.DomainUserLabel

object LocalLabelMapper : Mapper<DomainUserLabel, UserLabel> {
    override fun toDomain(data: DomainUserLabel): UserLabel {
        return UserLabel(data.color ?: "", data.text)
    }

    override fun fromDomain(data: UserLabel): DomainUserLabel {
        return DomainUserLabel(data.color, data.text)
    }
}