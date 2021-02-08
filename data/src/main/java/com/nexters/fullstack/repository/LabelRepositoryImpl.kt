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

    override fun remoteLoad(): Single<List<DomainLabel>> {
        return labelaryRemoteDataSource.load()
    }

    override fun remoteSave(labels: Pair<List<DomainLabel>, LocalImageDomain>): Completable {
        return labelaryRemoteDataSource.save(labels)
    }

    override fun localLoad(): Single<List<DomainLabel>> {
        return labelaryLocalDataSource.localLoad()
    }

    override fun localSave(labels: Pair<List<DomainLabel>, LocalImageDomain>): Completable {
        return labelaryLocalDataSource.localSave(labels)
    }
}