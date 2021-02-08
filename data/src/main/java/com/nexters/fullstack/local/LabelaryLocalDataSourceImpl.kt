package com.nexters.fullstack.local

import com.nexters.fullstack.db.dao.LabelDAO
import com.nexters.fullstack.mapper.local.LocalLabelMapper
import com.nexters.fullstack.source.local.DomainUserLabel
import io.reactivex.Completable
import io.reactivex.Maybe

class LabelaryLocalDataSourceImpl(private val labelDAO: LabelDAO) :
    LabelaryLocalDataSource {
    override fun save(label: DomainUserLabel): Completable {
        return labelDAO.save(LocalLabelMapper.toDomain(label))
    }

    override fun update(label: DomainUserLabel): Completable {
        return labelDAO.update(LocalLabelMapper.toDomain(label))
    }

    override fun delete(label: DomainUserLabel): Completable {
        return labelDAO.delete(LocalLabelMapper.toDomain(label))
    }

    override fun load(): Maybe<List<DomainUserLabel>> {
        return labelDAO.load().map { labels ->
            labels.map(LocalLabelMapper::fromDomain)
        }
    }
}
