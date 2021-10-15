package com.nexters.fullstack.domain.usecase

import com.nexters.fullstack.domain.entity.ImageEntity
import com.nexters.fullstack.domain.entity.LabelEntity
import com.nexters.fullstack.domain.repository.ImageRepository
import com.nexters.fullstack.domain.usecase.base.BaseUseCase
import io.reactivex.Single

class SearchImagesBySingleLabel constructor(
    private val imageRepository: ImageRepository
) : BaseUseCase<LabelEntity, Single<List<ImageEntity>>> {

    override fun buildUseCase(params: LabelEntity): Single<List<ImageEntity>> {
        return imageRepository.searchByLabel(params)
    }

}
