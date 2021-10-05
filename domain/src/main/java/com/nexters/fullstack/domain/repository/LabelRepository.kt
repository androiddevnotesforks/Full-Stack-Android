package com.nexters.fullstack.domain.repository

import com.nexters.fullstack.domain.entity.LabelEntity
import io.reactivex.Completable
import io.reactivex.Maybe

interface LabelRepository {
    fun insertOrUpdate(label: LabelEntity): Completable

    fun delete(label: LabelEntity): Completable

    fun load(): Maybe<List<LabelEntity>>
}