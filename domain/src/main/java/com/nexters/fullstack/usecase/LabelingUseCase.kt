package com.nexters.fullstack.usecase

import com.nexters.fullstack.source.data.LocalImageDomain
import com.nexters.fullstack.usecase.base.BaseUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class LabelingUseCase : BaseUseCase<Pair<List<Label>, LocalImageDomain>, Single<List<Label>>> {
    override fun buildUseCase(params: Pair<List<Label>, LocalImageDomain>): Single<List<Label>> {
        //subscribeOn BaseUseCase 로 옮기는건 고민 중.
        return Single.just(params.first).subscribeOn(Schedulers.computation())
    }
}


data class Label(val text: String)