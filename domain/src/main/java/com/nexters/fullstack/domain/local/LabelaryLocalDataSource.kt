package com.nexters.fullstack.domain.local

import com.nexters.fullstack.domain.entity.DomainUserImage
import com.nexters.fullstack.domain.entity.DomainUserLabel
import io.reactivex.Completable
import io.reactivex.Maybe


interface LabelaryLocalDataSource {
    interface Label {
        fun insertOrUpdate(label: DomainUserLabel): Completable

        fun delete(label: DomainUserLabel): Completable

        fun labelLoad(): Maybe<List<DomainUserLabel>>
    }

    interface Image {
        fun insertOrUpdate(image: DomainUserImage): Completable

        fun delete(image: DomainUserImage): Completable

        fun imageLoad(): Maybe<List<DomainUserImage>>
    }
}