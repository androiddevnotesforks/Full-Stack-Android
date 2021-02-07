package com.nexters.fullstack.local

import com.nexters.fullstack.source.DomainLabel
import com.nexters.fullstack.source.data.LocalImageDomain
import io.reactivex.Completable
import io.reactivex.Single


interface LabelaryLocalDataSource {
    fun localSave(labels: Pair<List<DomainLabel>, LocalImageDomain>): Completable

    fun localLoad(): Single<List<DomainLabel>>
}