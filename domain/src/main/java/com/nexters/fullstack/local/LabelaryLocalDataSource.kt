package com.nexters.fullstack.local

import com.nexters.fullstack.source.DomainLabel
import com.nexters.fullstack.source.data.LocalImageDomain
import com.nexters.fullstack.source.local.DomainUserLabel
import io.reactivex.Completable
import io.reactivex.Single


interface LabelaryLocalDataSource {
    fun save(label: DomainUserLabel): Completable

    fun update(label: DomainUserLabel): Completable

    fun delete(label: DomainUserLabel): Completable
}