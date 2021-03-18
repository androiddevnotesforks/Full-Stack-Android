package com.nexters.fullstack.usecase

import com.nexters.fullstack.repository.LabelRepository
import com.nexters.fullstack.source.DomainLabel
import com.nexters.fullstack.source.local.DomainUserLabel
import com.nexters.fullstack.usecase.base.BaseUseCase
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

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