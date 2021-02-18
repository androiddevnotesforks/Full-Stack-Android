package com.nexters.fullstack.repository

import io.reactivex.Completable

interface ImageRepository {
    fun update(): Completable

    fun save(): Completable

    fun delete(): Completable

    fun load(): Completable
}