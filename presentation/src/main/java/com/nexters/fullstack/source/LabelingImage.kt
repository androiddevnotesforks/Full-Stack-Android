package com.nexters.fullstack.source

import com.nexters.fullstack.source.data.LocalImageDomain
import com.nexters.fullstack.source.local.DomainUserLabel

data class LabelingImage(val domainLabel: DomainUserLabel, val localImages: List<LocalImageDomain>)
