package com.nexters.fullstack.repository

import com.nexters.fullstack.local.LabelaryLocalDataSource
import com.nexters.fullstack.remote.LabelaryRemoteDataSource
import com.nexters.fullstack.source.DomainLabel
import com.nexters.fullstack.source.data.LocalImageDomain
import com.nexters.fullstack.source.local.DomainUserLabel
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class LabelRepositoryImpl(
    private val labelaryLocalDataSource: LabelaryLocalDataSource.Label
) : LabelRepository {

//    override fun remoteLoad(): Single<List<DomainLabel>> {
//        return labelaryRemoteDataSource.load()
//    }
//
//    override fun remoteSave(labels: Pair<List<DomainLabel>, LocalImageDomain>): Completable {
//        return labelaryRemoteDataSource.save(labels)
//    }

    override fun update(label: DomainUserLabel): Completable {
        return labelaryLocalDataSource.update(label)
    }

    override fun save(label: DomainUserLabel): Completable {
        return labelaryLocalDataSource.save(label)
    }

    override fun delete(label: DomainUserLabel): Completable {
        return labelaryLocalDataSource.delete(label)
    }

    override fun load(): Maybe<List<DomainUserLabel>> {
        return labelaryLocalDataSource.labelLoad()
    }
}