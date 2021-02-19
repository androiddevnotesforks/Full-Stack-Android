package com.nexters.fullstack.local

import com.nexters.fullstack.source.local.DomainUserImage
import com.nexters.fullstack.source.local.DomainUserLabel
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

//    fun save(label: DomainUserLabel): Completable
//
//    fun update(label: DomainUserLabel): Completable
//
//    fun delete(label: DomainUserLabel): Completable
//
//    fun load(): Maybe<List<DomainUserLabel>>
}