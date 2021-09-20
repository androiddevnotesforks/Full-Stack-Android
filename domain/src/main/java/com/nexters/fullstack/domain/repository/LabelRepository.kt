package com.nexters.fullstack.domain.repository

import com.nexters.fullstack.domain.source.local.DomainUserLabel
import io.reactivex.Completable
import io.reactivex.Maybe

interface LabelRepository {
    fun update(label: DomainUserLabel): Completable

    fun save(label: DomainUserLabel): Completable

    fun delete(label: DomainUserLabel): Completable

    fun load(): Maybe<List<DomainUserLabel>>
}