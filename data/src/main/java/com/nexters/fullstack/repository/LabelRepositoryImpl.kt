package com.nexters.fullstack.repository

import com.nexters.fullstack.local.LabelaryLocalDataSource
import com.nexters.fullstack.remote.LabelaryRemoteDataSource
import com.nexters.fullstack.source.DomainLabel
import com.nexters.fullstack.source.data.LocalImageDomain
import io.reactivex.Completable
import io.reactivex.Single

class LabelRepositoryImpl(
    private val labelaryRemoteDataSource: LabelaryRemoteDataSource,
    private val labelaryLocalDataSource: LabelaryLocalDataSource
) : LabelRepository {
    override fun load(): Single<List<DomainLabel>> {
        return labelaryRemoteDataSource.load()
    }

    override fun save(labels: Pair<List<DomainLabel>, LocalImageDomain>): Completable {
        return labelaryRemoteDataSource.save(labels)
    }
}