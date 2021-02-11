package com.nexters.fullstack.repository

import com.nexters.fullstack.source.local.DomainUserLabel
import io.reactivex.Completable
import io.reactivex.Maybe

interface LabelRepository {
//    fun remoteLoad(): Single<List<DomainLabel>>
//
//    fun remoteSave(labels: Pair<List<DomainLabel>, LocalImageDomain>): Completable

    fun update(label: DomainUserLabel): Completable

    fun save(label: DomainUserLabel): Completable

    fun delete(label: DomainUserLabel): Completable

    fun load(): Maybe<List<DomainUserLabel>>
}