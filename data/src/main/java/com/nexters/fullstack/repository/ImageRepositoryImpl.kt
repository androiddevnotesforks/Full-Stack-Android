package com.nexters.fullstack.repository

import com.nexters.fullstack.local.LabelaryLocalDataSource
import com.nexters.fullstack.source.local.DomainUserImage
import io.reactivex.Completable
import io.reactivex.Maybe

class ImageRepositoryImpl(private val labelaryLocalDataSource: LabelaryLocalDataSource.Image) :
    ImageRepository {
    override fun update(data: DomainUserImage): Completable {
        return labelaryLocalDataSource.update(data)
    }

    override fun save(data: DomainUserImage): Completable {
        return labelaryLocalDataSource.save(data)
    }

    override fun delete(data: DomainUserImage): Completable {
        return labelaryLocalDataSource.delete(data)
    }

    override fun load(): Maybe<List<DomainUserImage>> {
        return labelaryLocalDataSource.imageLoad()
    }

}