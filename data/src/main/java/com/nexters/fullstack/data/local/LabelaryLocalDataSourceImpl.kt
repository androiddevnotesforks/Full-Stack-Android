package com.nexters.fullstack.data.local

import com.nexters.fullstack.data.db.dao.ImageDAO
import com.nexters.fullstack.data.db.dao.LabelDAO
import com.nexters.fullstack.data.mapper.UserLabelingImageMapper
import com.nexters.fullstack.data.mapper.LocalLabelMapper
import com.nexters.fullstack.domain.local.LabelaryLocalDataSource
import com.nexters.fullstack.domain.entity.DomainUserImage
import com.nexters.fullstack.domain.entity.DomainUserLabel
import io.reactivex.Completable
import io.reactivex.Maybe

class LabelaryLocalDataSourceImpl(private val labelDAO: LabelDAO, private val imageDAO: ImageDAO) :
    LabelaryLocalDataSource.Label, LabelaryLocalDataSource.Image {
    override fun insertOrUpdate(label: DomainUserLabel): Completable {
        return labelDAO.insertOrUpdate(LocalLabelMapper.toData(label))
    }

    override fun delete(label: DomainUserLabel): Completable {
        return labelDAO.delete(LocalLabelMapper.toData(label))
    }

    override fun labelLoad(): Maybe<List<DomainUserLabel>> {
        return labelDAO.load().map { labels ->
            labels.map(LocalLabelMapper::fromData)
        }
    }

    override fun insertOrUpdate(image: DomainUserImage): Completable {
        return imageDAO.insertOrUpdate(
            UserLabelingImageMapper.fromData(image)
        )
    }

    override fun delete(image: DomainUserImage): Completable {
        return imageDAO.delete(UserLabelingImageMapper.fromData(image))
    }

    override fun imageLoad(): Maybe<List<DomainUserImage>> {
        return imageDAO.load().map { userImages ->
            userImages.map { UserLabelingImageMapper.toData(it) }
        }
    }
}
