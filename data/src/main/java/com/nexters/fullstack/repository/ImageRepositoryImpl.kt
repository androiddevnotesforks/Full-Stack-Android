package com.nexters.fullstack.repository

import com.nexters.fullstack.local.LabelaryLocalDataSource
import io.reactivex.Completable

class ImageRepositoryImpl(private val labelaryLocalDataSource: LabelaryLocalDataSource.Image) : ImageRepository {
    override fun update(): Completable {
        TODO("Not yet implemented")
    }

    override fun save(): Completable {
        TODO("Not yet implemented")
    }

    override fun delete(): Completable {
        TODO("Not yet implemented")
    }

    override fun load(): Completable {
        TODO("Not yet implemented")
    }
}