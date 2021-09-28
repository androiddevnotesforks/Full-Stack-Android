package com.nexters.fullstack.domain.repository

import com.nexters.fullstack.domain.entity.DomainUserLabel
import io.reactivex.Completable
import io.reactivex.Maybe

interface LabelRepository {
    fun insertOrUpdate(label: DomainUserLabel): Completable

    fun delete(label: DomainUserLabel): Completable

    fun load(): Maybe<List<DomainUserLabel>>
}