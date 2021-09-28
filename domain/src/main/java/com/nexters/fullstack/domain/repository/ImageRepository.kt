package com.nexters.fullstack.domain.repository

import com.nexters.fullstack.domain.entity.DomainUserImage
import io.reactivex.Completable
import io.reactivex.Maybe

interface ImageRepository {
    fun insertOrUpdate(data: DomainUserImage): Completable

    fun delete(data: DomainUserImage): Completable

    fun load(): Maybe<List<DomainUserImage>>
}