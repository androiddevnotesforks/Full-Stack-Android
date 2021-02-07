package com.nexters.fullstack.repository

import com.nexters.fullstack.source.DomainLabel
import com.nexters.fullstack.source.data.LocalImageDomain
import io.reactivex.Completable
import io.reactivex.Single

interface LabelRepository {
    fun load(): Single<List<DomainLabel>>

    fun save(labels: Pair<List<DomainLabel>, LocalImageDomain>): Completable
}