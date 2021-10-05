package com.nexters.fullstack.data.repository

import com.nexters.fullstack.domain.local.LabelaryLocalDataSource
import com.nexters.fullstack.domain.repository.LabelRepository
import com.nexters.fullstack.domain.entity.LabelEntity
import io.reactivex.Completable
import io.reactivex.Maybe

class LabelRepositoryImpl(
    private val labelaryLocalDataSourceLabel: LabelaryLocalDataSource.Label
) : LabelRepository {

    override fun insertOrUpdate(label: LabelEntity): Completable {
        return labelaryLocalDataSourceLabel.insertOrUpdate(label)
    }

    override fun delete(label: LabelEntity): Completable {
        return labelaryLocalDataSourceLabel.delete(label)
    }

    override fun load(): Maybe<List<LabelEntity>> {
        return labelaryLocalDataSourceLabel.labelLoad()
    }
}