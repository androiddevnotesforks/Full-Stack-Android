package com.nexters.fullstack.domain.repository

import com.nexters.fullstack.domain.entity.ImageEntity
import io.reactivex.Completable
import io.reactivex.Maybe

interface ImageRepository {
    fun insertOrUpdate(data: ImageEntity): Completable

    fun delete(data: ImageEntity): Completable

    fun load(): Maybe<List<ImageEntity>>
}