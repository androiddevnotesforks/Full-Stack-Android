package com.nexters.fullstack.data.repository

import com.nexters.fullstack.domain.local.LabelaryLocalDataSource
import com.nexters.fullstack.domain.repository.LabelRepository
import com.nexters.fullstack.domain.entity.DomainUserLabel
import io.reactivex.Completable
import io.reactivex.Maybe

class LabelRepositoryImpl(
    private val labelaryLocalDataSourceLabel: LabelaryLocalDataSource.Label
) : LabelRepository {

    override fun insertOrUpdate(label: DomainUserLabel): Completable {
        return labelaryLocalDataSourceLabel.insertOrUpdate(label)
    }

    override fun delete(label: DomainUserLabel): Completable {
        return labelaryLocalDataSourceLabel.delete(label)
    }

    override fun load(): Maybe<List<DomainUserLabel>> {
        return labelaryLocalDataSourceLabel.labelLoad()
    }
}