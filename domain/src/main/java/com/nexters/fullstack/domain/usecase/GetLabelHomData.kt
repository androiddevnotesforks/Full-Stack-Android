package com.nexters.fullstack.domain.usecase

import com.nexters.fullstack.domain.entity.LabelEntity
import com.nexters.fullstack.domain.repository.LabelRepository
import com.nexters.fullstack.domain.usecase.base.BaseUseCase
import io.reactivex.Single

class GetLabelHomData(
    private val labelRepository: LabelRepository
) : BaseUseCase<String, Single<GetLabelHomData.Result>> {
    override fun buildUseCase(params: String): Single<Result> {
        return labelRepository.loadWithImages().map(::Result)
    }


    data class Result(
        val labels: List<Pair<LabelEntity, Int>>,
    )
}