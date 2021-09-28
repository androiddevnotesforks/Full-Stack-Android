package com.nexters.fullstack.domain.usecase

import com.nexters.fullstack.domain.repository.LabelRepository
import com.nexters.fullstack.domain.entity.DomainUserLabel
import com.nexters.fullstack.domain.usecase.base.BaseUseCase
import io.reactivex.Completable
import io.reactivex.Maybe

class GetLabelManagementUseCase(private val labelRepository: LabelRepository) :
    BaseUseCase<Unit, Maybe<List<DomainUserLabel>>> {
    override fun buildUseCase(params: Unit): Maybe<List<DomainUserLabel>> {
        return labelRepository.load()
    }


    fun insertOrUpdate(label: DomainUserLabel): Completable {
        return labelRepository.insertOrUpdate(label)
    }
}