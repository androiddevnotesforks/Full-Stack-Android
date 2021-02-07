package com.nexters.fullstack.usecase

import com.nexters.fullstack.repository.AlbumRepository
import com.nexters.fullstack.source.data.LocalLabelDomain
import com.nexters.fullstack.usecase.base.BaseUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

internal class AlbumLoadUseCase(private val albumRepository: AlbumRepository) :
    BaseUseCase<String, Single<List<LocalLabelDomain>?>> {
    override fun buildUseCase(params: String): Single<List<LocalLabelDomain>?> {
        return Single.just(albumRepository.getUnLabeling(params))
//            .subscribeOn(Schedulers.computation())
    }
}