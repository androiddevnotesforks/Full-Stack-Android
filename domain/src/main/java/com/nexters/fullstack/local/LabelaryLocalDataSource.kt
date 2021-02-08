package com.nexters.fullstack.local

import com.nexters.fullstack.source.local.DomainUserLabel
import io.reactivex.Completable
import io.reactivex.Maybe


interface LabelaryLocalDataSource {
    fun save(label: DomainUserLabel): Completable

    fun update(label: DomainUserLabel): Completable

    fun delete(label: DomainUserLabel): Completable

    fun load(): Maybe<List<DomainUserLabel>>
}