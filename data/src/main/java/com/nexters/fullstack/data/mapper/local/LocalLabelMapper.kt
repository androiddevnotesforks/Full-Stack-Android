package com.nexters.fullstack.data.mapper.local

import com.nexters.fullstack.data.db.entity.UserLabel
import com.nexters.fullstack.data.mapper.Mapper
import com.nexters.fullstack.domain.source.local.DomainUserLabel

object LocalLabelMapper : Mapper<DomainUserLabel, UserLabel> {
    override fun toDomain(data: DomainUserLabel): UserLabel {
        return UserLabel(color = data.color ?: "", text = data.text)
    }

    override fun fromDomain(data: UserLabel): DomainUserLabel {
        return DomainUserLabel(data.color, data.text)
    }
}