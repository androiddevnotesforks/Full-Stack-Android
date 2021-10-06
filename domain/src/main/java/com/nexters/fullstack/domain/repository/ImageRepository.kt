package com.nexters.fullstack.domain.repository

import com.nexters.fullstack.domain.entity.ImageEntity
import com.nexters.fullstack.domain.entity.LabelEntity
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface ImageRepository {
    fun insertOrUpdate(data: ImageEntity): Completable

    fun insertOrUpdate(data: List<ImageEntity>): Completable

    fun delete(data: ImageEntity): Completable

    fun delete(data: List<ImageEntity>): Completable

    fun load(): Single<List<ImageEntity>>

    fun searchByLabels(labels : List<LabelEntity>): Single<List<ImageEntity>>
}