package com.nexters.fullstack.domain.local

import com.nexters.fullstack.domain.entity.ImageEntity
import com.nexters.fullstack.domain.entity.LabelEntity
import io.reactivex.Completable
import io.reactivex.Maybe


interface LabelaryLocalDataSource {
    interface Label {
        fun insertOrUpdate(label: LabelEntity): Completable

        fun delete(label: LabelEntity): Completable

        fun labelLoad(): Maybe<List<LabelEntity>>
    }

    interface Image {
        fun insertOrUpdate(image: ImageEntity): Completable

        fun delete(image: ImageEntity): Completable

        fun imageLoad(): Maybe<List<ImageEntity>>
    }
}