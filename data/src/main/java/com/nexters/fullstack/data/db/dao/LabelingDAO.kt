package com.nexters.fullstack.data.db.dao

import androidx.room.*
import com.nexters.fullstack.data.db.entity.ImageModel
import com.nexters.fullstack.data.db.entity.ImageWithLabels
import com.nexters.fullstack.data.db.entity.LabelModel
import com.nexters.fullstack.data.db.entity.LabelingRelationRef
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface LabelingDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(relations: List<LabelingRelationRef>): Completable

    @Delete
    fun delete(relations: List<LabelingRelationRef>): Completable
}