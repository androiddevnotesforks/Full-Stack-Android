package com.nexters.fullstack.usecase

import com.nexters.fullstack.repository.LabelRepository
import com.nexters.fullstack.source.DomainLabel
import com.nexters.fullstack.usecase.base.BaseUseCase
import io.reactivex.Single

class LoadLabelUseCase(private val labelRepository: LabelRepository) :
    BaseUseCase<Unit, Single<List<DomainLabel>>> {
    override fun buildUseCase(params: Unit): Single<List<DomainLabel>> {
        return labelRepository.remoteLoad()
    }
}