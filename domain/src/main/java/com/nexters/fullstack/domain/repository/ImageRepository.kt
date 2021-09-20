package com.nexters.fullstack.domain.repository

import com.nexters.fullstack.domain.source.local.DomainUserImage
import io.reactivex.Completable
import io.reactivex.Maybe

interface ImageRepository {
    fun update(data: DomainUserImage): Completable

    fun save(data: DomainUserImage): Completable

    fun delete(data: DomainUserImage): Completable

    fun load(): Maybe<List<DomainUserImage>>
}