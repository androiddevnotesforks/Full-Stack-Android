package com.nexters.fullstack.repository

import com.nexters.fullstack.source.DomainLabel
import com.nexters.fullstack.source.data.LocalImageDomain
import io.reactivex.Completable
import io.reactivex.Single

interface LabelRepository {
    fun remoteLoad(): Single<List<DomainLabel>>

    fun remoteSave(labels: Pair<List<DomainLabel>, LocalImageDomain>): Completable

    fun localLoad(): Single<List<DomainLabel>>

    fun localSave(labels: Pair<List<DomainLabel>, LocalImageDomain>): Completable
}