package com.nexters.fullstack.domain.usecase

import com.nexters.fullstack.domain.source.DomainLabel
import com.nexters.fullstack.domain.usecase.base.BaseUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class SearchLabelUseCase : BaseUseCase<DomainLabel, Single<MutableList<DomainLabel>>> {
    private val labels = mutableListOf<DomainLabel>()

    override fun buildUseCase(params: DomainLabel): Single<MutableList<DomainLabel>> {
        if (labels.contains(params)) {
            labels.remove(params)
        } else {
            labels.add(params)
        }

        return Single.just(labels).subscribeOn(Schedulers.computation())
    }
}