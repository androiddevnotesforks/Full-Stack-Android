package com.nexters.fullstack.usecase

import com.nexters.fullstack.repository.LabelRepository
import com.nexters.fullstack.usecase.base.BaseUseCase
import io.reactivex.Single

class LoadLabelUseCase(private val labelRepository: LabelRepository) :
    BaseUseCase<Unit, Single<List<Label>>> {
    override fun buildUseCase(params: Unit): Single<List<Label>> {
        return labelRepository.load()
    }
}