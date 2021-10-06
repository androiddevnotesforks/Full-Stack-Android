package com.nexters.fullstack.domain.usecase

import com.nexters.fullstack.domain.entity.LabelEntity
import com.nexters.fullstack.domain.repository.LabelRepository
import com.nexters.fullstack.domain.usecase.base.BaseUseCase
import io.reactivex.Single

class GetSearchHomData(
    private val labelRepository: LabelRepository
) : BaseUseCase<String, Single<GetSearchHomData.Result>> {

    override fun buildUseCase(params: String): Single<Result> {
        return Single.zip(
            labelRepository.loadAll(),
            labelRepository.loadRecentlyLabels(),
            ::Result
        )
    }


    data class Result(
        val recentlyLabels : List<LabelEntity>,
        val labels : List<LabelEntity>
    )
}