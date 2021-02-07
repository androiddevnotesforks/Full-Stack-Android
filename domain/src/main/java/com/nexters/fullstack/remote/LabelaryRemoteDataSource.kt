package com.nexters.fullstack.remote

import com.nexters.fullstack.source.DomainLabel
import com.nexters.fullstack.source.data.LocalImageDomain
import io.reactivex.Completable
import io.reactivex.Single

interface LabelaryRemoteDataSource {
    fun load(): Single<List<DomainLabel>>

    fun save(labels: Pair<List<DomainLabel>, LocalImageDomain>): Completable
}