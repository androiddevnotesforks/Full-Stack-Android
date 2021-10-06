package com.nexters.fullstack.data.local

import com.nexters.fullstack.data.db.dao.ImageDAO
import com.nexters.fullstack.data.db.dao.LabelDAO
import com.nexters.fullstack.data.db.dao.LabelingDAO
import com.nexters.fullstack.data.db.entity.LabelingRelationRef
import com.nexters.fullstack.data.mapper.ImageModelMapper
import com.nexters.fullstack.data.mapper.LabelModelMapper
import com.nexters.fullstack.domain.local.LabelaryLocalDataSource
import com.nexters.fullstack.domain.entity.ImageEntity
import com.nexters.fullstack.domain.entity.LabelEntity
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class LabelaryLocalDataSourceImpl(
    private val labelDAO: LabelDAO,
    private val imageDAO: ImageDAO,
    private val labelingDao: LabelingDAO
) : LabelaryLocalDataSource.Label, LabelaryLocalDataSource.Image {
    override fun insertOrUpdate(label: LabelEntity): Completable {
        return labelDAO.insertOrUpdate(LabelModelMapper.toData(label))
    }

    override fun delete(label: LabelEntity): Completable {
        return labelDAO.delete(LabelModelMapper.toData(label))
    }

    override fun labelLoad(): Single<List<LabelEntity>> {
        return labelDAO.load().map { labels ->
            labels.map(LabelModelMapper::fromData)
        }
    }

    override fun loadWithImages(): Single<List<Pair<LabelEntity, Int>>> {
        return labelDAO.loadWithImages().map { labels ->
            labels.map { LabelModelMapper.fromData(it.label) to it.images.size }
        }
    }

    override fun loadRecentlyLabels(): Single<List<LabelEntity>> {
        return labelingDao.loadRecentlyLabels().map { labels ->
            labels.map(LabelModelMapper::fromData)
        }
    }

    override fun searchLabel(keyword: String): Single<List<LabelEntity>> {
        return labelDAO.searchLabels(keyword).map {
            it.map(LabelModelMapper::fromData)
        }
    }

    override fun insertOrUpdate(image: List<ImageEntity>): Completable {
        return Completable.concat(image.map { insertOrUpdate(it) })
    }

    override fun insertOrUpdate(image: ImageEntity): Completable {
        return imageDAO.insertOrUpdate(ImageModelMapper.fromData(image).image)
            .andThen(labelingDao.delete(arrayOf(image.image.id)))
            .andThen(labelingDao.insertOrUpdate(
                ImageModelMapper.fromData(image).run {
                    labels.map {
                        LabelingRelationRef(imageId = this.image.imageId, labelId = it.labelId)
                    }
                }
            ))
    }

    override fun delete(image: ImageEntity): Completable {
        return imageDAO.delete(ImageModelMapper.fromData(image).image)
    }

    override fun imageLoad(): Single<List<ImageEntity>> {
        return imageDAO.load().map { userImages ->
            userImages.map { ImageModelMapper.toData(it) }
        }
    }

    override fun searchByLabels(labels: List<LabelEntity>): Single<List<ImageEntity>> {
        return imageDAO.searchByLabels(labels.map { it.id }).map { userImages ->
            userImages.map { ImageModelMapper.toData(it) }
        }
    }
}
