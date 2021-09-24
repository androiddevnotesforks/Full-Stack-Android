package com.nexters.fullstack.ui.adapter.listener

import com.nexters.fullstack.domain.source.local.DomainUserLabel

interface ItemClickListener {
    fun onClick(item: DomainUserLabel)
}