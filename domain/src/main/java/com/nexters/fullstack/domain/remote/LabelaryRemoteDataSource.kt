package com.nexters.fullstack.domain.remote

import com.nexters.fullstack.domain.entity.DomainLabel
import com.nexters.fullstack.domain.entity.LocalImageDomain
import io.reactivex.Completable
import io.reactivex.Single

interface LabelaryRemoteDataSource {
    fun load(): Single<List<DomainLabel>>

    fun save(labels: Pair<List<DomainLabel>, LocalImageDomain>): Completable
}