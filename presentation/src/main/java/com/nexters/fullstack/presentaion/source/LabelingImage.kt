package com.nexters.fullstack.presentaion.source

import com.nexters.fullstack.domain.source.data.LocalImageDomain
import com.nexters.fullstack.domain.source.local.DomainUserLabel

data class LabelingImage(
    val domainLabel: DomainUserLabel,
    val localImages: List<LocalImageDomain>
)
