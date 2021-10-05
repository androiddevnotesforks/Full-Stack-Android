package com.nexters.fullstack.data.repository

import com.nexters.fullstack.domain.local.LabelaryLocalDataSource
import com.nexters.fullstack.domain.repository.ImageRepository
import com.nexters.fullstack.domain.entity.ImageEntity
import io.reactivex.Completable
import io.reactivex.Maybe

class ImageRepositoryImpl(private val labelaryLocalDataSource: LabelaryLocalDataSource.Image) :
    ImageRepository {
    override fun insertOrUpdate(data: ImageEntity): Completable {
        return labelaryLocalDataSource.insertOrUpdate(data)
    }

    override fun delete(data: ImageEntity): Completable {
        return labelaryLocalDataSource.delete(data)
    }

    override fun load(): Maybe<List<ImageEntity>> {
        return labelaryLocalDataSource.imageLoad()
    }
}