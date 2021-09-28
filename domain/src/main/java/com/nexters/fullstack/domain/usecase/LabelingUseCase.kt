package com.nexters.fullstack.domain.usecase

import com.nexters.fullstack.domain.repository.LabelRepository
import com.nexters.fullstack.domain.entity.DomainUserLabel
import com.nexters.fullstack.domain.usecase.base.BaseUseCase
import io.reactivex.Completable

class LabelingUseCase(private val labelRepository: LabelRepository) :
    BaseUseCase<DomainUserLabel, Completable> {
    override fun buildUseCase(params: DomainUserLabel): Completable {
        return labelRepository.insertOrUpdate(params)
    }
}


