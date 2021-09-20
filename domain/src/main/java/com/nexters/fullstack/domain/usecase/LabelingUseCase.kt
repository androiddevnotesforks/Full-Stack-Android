package com.nexters.fullstack.domain.usecase

import com.nexters.fullstack.domain.repository.LabelRepository
import com.nexters.fullstack.domain.source.local.DomainUserLabel
import com.nexters.fullstack.domain.usecase.base.BaseUseCase
import io.reactivex.Completable

class LabelingUseCase(private val labelRepository: LabelRepository) :
    BaseUseCase<DomainUserLabel, Completable> {
    override fun buildUseCase(params: DomainUserLabel): Completable {
        //subscribeOn BaseUseCase 로 옮기는건 고민 중.
        return labelRepository.save(params)
    }

    fun update(params: DomainUserLabel): Completable {
        return labelRepository.update(params)
    }
}


