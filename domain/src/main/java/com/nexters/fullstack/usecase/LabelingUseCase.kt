package com.nexters.fullstack.usecase

import com.nexters.fullstack.repository.LabelRepository
import com.nexters.fullstack.source.DomainLabel
import com.nexters.fullstack.source.data.LocalImageDomain
import com.nexters.fullstack.usecase.base.BaseUseCase
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class LabelingUseCase(private val labelRepository: LabelRepository) :
    BaseUseCase<Pair<List<DomainLabel>, LocalImageDomain>, Completable> {
    override fun buildUseCase(params: Pair<List<DomainLabel>, LocalImageDomain>): Completable {
        //subscribeOn BaseUseCase 로 옮기는건 고민 중.
        return labelRepository.localSave(params)
    }
}


