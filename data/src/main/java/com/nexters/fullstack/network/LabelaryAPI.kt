package com.nexters.fullstack.network

import com.nexters.fullstack.source.DomainLabel
import com.nexters.fullstack.source.data.LocalImageDomain
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