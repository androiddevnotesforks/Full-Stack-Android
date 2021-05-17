package com.nexters.fullstack.source

import com.nexters.fullstack.source.data.LocalImageDomain
import com.nexters.fullstack.source.local.DomainUserLabel
import java.io.Serializable

data class LabelingImage(
    val domainLabel: DomainUserLabel,
    val localImages: List<LocalImageDomain>
)
