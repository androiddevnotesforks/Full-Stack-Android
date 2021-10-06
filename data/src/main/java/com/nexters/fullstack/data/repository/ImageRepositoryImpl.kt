package com.nexters.fullstack.data.repository

import com.nexters.fullstack.domain.local.LabelaryLocalDataSource
import com.nexters.fullstack.domain.repository.ImageRepository
import com.nexters.fullstack.domain.entity.ImageEntity
import com.nexters.fullstack.domain.entity.LabelEntity
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class ImageRepositoryImpl(private val labelaryLocalDataSource: LabelaryLocalDataSource.Image) :
    ImageRepository {
    override fun insertOrUpdate(data: ImageEntity): Completable {
        return labelaryLocalDataSource.insertOrUpdate(data)
    }

    override fun insertOrUpdate(data: List<ImageEntity>): Completable {
        return labelaryLocalDataSource.insertOrUpdate(data)
    }

    override fun delete(data: ImageEntity): Completable {
        return labelaryLocalDataSource.delete(data)
    }

    override fun delete(data: List<ImageEntity>): Completable =
        Completable.concat(data.map(::delete))

    override fun load(): Single<List<ImageEntity>> {
        return labelaryLocalDataSource.imageLoad()
    }

    override fun searchByLabels(labels: List<LabelEntity>): Single<List<ImageEntity>> {
        return labelaryLocalDataSource.searchByLabels(labels)
    }
}