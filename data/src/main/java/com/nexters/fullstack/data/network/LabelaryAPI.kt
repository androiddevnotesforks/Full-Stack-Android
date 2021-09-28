package com.nexters.fullstack.data.network

import com.nexters.fullstack.domain.entity.DomainLabel
import com.nexters.fullstack.domain.entity.LocalImageDomain
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST

interface LabelaryAPI {
    @GET("")
    fun load(): Single<List<DomainLabel>>

    @POST("")
    fun save(labels: Pair<List<DomainLabel>, LocalImageDomain>): Completable
}