package com.nexters.fullstack.usecase

import com.nexters.fullstack.usecase.base.BaseUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class SearchLabelUseCase : BaseUseCase<Label, Single<MutableList<Label>>> {
    private val labels = mutableListOf<Label>()

    override fun buildUseCase(params: Label): Single<MutableList<Label>> {
        if (labels.contains(params)) {
            labels.remove(params)
        } else {
            labels.add(params)
        }

        return Single.just(labels).subscribeOn(Schedulers.computation())
    }
}