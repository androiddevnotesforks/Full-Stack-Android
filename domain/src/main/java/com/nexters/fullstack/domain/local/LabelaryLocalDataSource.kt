package com.nexters.fullstack.domain.local

import com.nexters.fullstack.domain.source.local.DomainUserImage
import com.nexters.fullstack.domain.source.local.DomainUserLabel
import io.reactivex.Completable
import io.reactivex.Maybe


interface LabelaryLocalDataSource {
    interface Label {
        fun save(label: DomainUserLabel): Completable

        fun update(label: DomainUserLabel): Completable

        fun delete(label: DomainUserLabel): Completable

        fun labelLoad(): Maybe<List<DomainUserLabel>>
    }

    interface Image {
        fun save(label: DomainUserImage): Completable

        fun update(label: DomainUserImage): Completable

        fun delete(label: DomainUserImage): Completable

        fun imageLoad(): Maybe<List<DomainUserImage>>
    }
}