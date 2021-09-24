package com.nexters.fullstack.data.repository

import com.nexters.fullstack.domain.local.LabelaryLocalDataSource
import com.nexters.fullstack.domain.repository.LabelRepository
import com.nexters.fullstack.domain.source.local.DomainUserLabel
import io.reactivex.Completable
import io.reactivex.Maybe

class LabelRepositoryImpl(
    private val labelaryLocalDataSourceLabel: LabelaryLocalDataSource.Label
) : LabelRepository {

//    override fun remoteLoad(): Single<List<DomainLabel>> {
//        return labelaryRemoteDataSource.load()
//    }
//
//    override fun remoteSave(labels: Pair<List<DomainLabel>, LocalImageDomain>): Completable {
//        return labelaryRemoteDataSource.save(labels)
//    }

    override fun update(label: DomainUserLabel): Completable {
        return labelaryLocalDataSourceLabel.update(label)
    }

    override fun save(label: DomainUserLabel): Completable {
        return labelaryLocalDataSourceLabel.save(label)
    }

    override fun delete(label: DomainUserLabel): Completable {
        return labelaryLocalDataSourceLabel.delete(label)
    }

    override fun load(): Maybe<List<DomainUserLabel>> {
        return labelaryLocalDataSourceLabel.labelLoad()
    }
}