package com.nexters.fullstack.repository

import com.nexters.fullstack.source.local.DomainUserImage
import io.reactivex.Completable
import io.reactivex.Maybe

interface ImageRepository {
    fun update(data: DomainUserImage): Completable

    fun save(data: DomainUserImage): Completable

    fun delete(data: DomainUserImage): Completable

    fun load(): Maybe<List<DomainUserImage>>
}