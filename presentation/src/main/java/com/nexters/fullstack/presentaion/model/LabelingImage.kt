package com.nexters.fullstack.presentaion.model

import com.nexters.fullstack.domain.entity.LocalImageDomain
import com.nexters.fullstack.domain.entity.DomainUserLabel

data class LabelingImage(
    val domainLabel: DomainUserLabel,
    val localImages: List<LocalImageDomain>
)
