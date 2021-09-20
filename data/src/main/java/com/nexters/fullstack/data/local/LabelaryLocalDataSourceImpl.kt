package com.nexters.fullstack.data.local

import com.nexters.fullstack.data.db.dao.ImageDAO
import com.nexters.fullstack.data.db.dao.LabelDAO
import com.nexters.fullstack.data.mapper.UserLabelingImageMapper
import com.nexters.fullstack.data.mapper.local.LocalLabelMapper
import com.nexters.fullstack.domain.local.LabelaryLocalDataSource
import com.nexters.fullstack.domain.source.local.DomainUserImage
import com.nexters.fullstack.domain.source.local.DomainUserLabel
import io.reactivex.Completable
import io.reactivex.Maybe

class LabelaryLocalDataSourceImpl(private val labelDAO: LabelDAO, private val imageDAO: ImageDAO) :
    LabelaryLocalDataSource.Label, LabelaryLocalDataSource.Image {
    override fun save(label: DomainUserLabel): Completable {
        return labelDAO.save(LocalLabelMapper.toDomain(label))
    }

    override fun update(label: DomainUserLabel): Completable {
        return labelDAO.update(LocalLabelMapper.toDomain(label))
    }

    override fun delete(label: DomainUserLabel): Completable {
        return labelDAO.delete(LocalLabelMapper.toDomain(label))
    }

    override fun labelLoad(): Maybe<List<DomainUserLabel>> {
        return labelDAO.load().map { labels ->
            labels.map(LocalLabelMapper::fromDomain)
        }
    }

    override fun save(label: DomainUserImage): Completable {
        return imageDAO.save(
            UserLabelingImageMapper.toDomain(label)
        )
    }

    override fun update(label: DomainUserImage): Completable {
        TODO("Not yet implemented")
    }

    override fun delete(label: DomainUserImage): Completable {
        TODO("Not yet implemented")
    }

    override fun imageLoad(): Maybe<List<DomainUserImage>> {
        return imageDAO.load().map { userImages ->
            userImages.map { UserLabelingImageMapper.fromDomain(it) }
        }
    }
}
