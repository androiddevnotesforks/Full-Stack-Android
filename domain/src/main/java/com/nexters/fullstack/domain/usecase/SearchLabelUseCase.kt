package com.nexters.fullstack.domain.usecase

import com.nexters.fullstack.domain.entity.LabelEntity
import com.nexters.fullstack.domain.usecase.base.BaseUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class SearchLabelUseCase : BaseUseCase<LabelEntity, Single<MutableList<LabelEntity>>> {
    private val labels = mutableListOf<LabelEntity>()

    override fun buildUseCase(params: LabelEntity): Single<MutableList<LabelEntity>> {
        if (labels.contains(params)) {
            labels.remove(params)
        } else {
            labels.add(params)
        }

        return Single.just(labels).subscribeOn(Schedulers.computation())
    }
}