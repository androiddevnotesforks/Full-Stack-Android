package com.nexters.fullstack.domain.usecase

import com.nexters.fullstack.domain.repository.LabelRepository
import com.nexters.fullstack.domain.entity.LabelEntity
import com.nexters.fullstack.domain.usecase.base.BaseUseCase
import io.reactivex.Maybe

class GetLabels(private val labelRepository: LabelRepository) :
    BaseUseCase<Unit, Maybe<List<LabelEntity>>> {
    override fun buildUseCase(params: Unit): Maybe<List<LabelEntity>> {
        return labelRepository.load()
    }
}