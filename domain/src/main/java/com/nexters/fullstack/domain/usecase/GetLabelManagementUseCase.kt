package com.nexters.fullstack.domain.usecase

import com.nexters.fullstack.domain.repository.LabelRepository
import com.nexters.fullstack.domain.source.local.DomainUserLabel
import com.nexters.fullstack.domain.usecase.base.BaseUseCase
import io.reactivex.Completable
import io.reactivex.Maybe

class GetLabelManagementUseCase(private val labelRepository: LabelRepository) :
    BaseUseCase<Unit, Maybe<List<DomainUserLabel>>> {
    override fun buildUseCase(params: Unit): Maybe<List<DomainUserLabel>> {
        return labelRepository.load()
    }

    fun update(label: DomainUserLabel): Completable {
        return labelRepository.update(label)
    }

    fun create(label: DomainUserLabel): Completable {
        return labelRepository.save(label)
    }
}