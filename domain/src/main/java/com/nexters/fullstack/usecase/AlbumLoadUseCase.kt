package com.nexters.fullstack.usecase

import com.nexters.fullstack.repository.AlbumRepository
import com.nexters.fullstack.source.data.LocalLabelDomain
import com.nexters.fullstack.usecase.base.BaseUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

internal class AlbumLoadUseCase(private val albumRepository: AlbumRepository) :
    BaseUseCase<LocalLabelDomain, Single<List<LocalLabelDomain>?>>() {
    override fun buildUseCase(params: LocalLabelDomain): Single<List<LocalLabelDomain>?> {
        return Single.just(albumRepository.getUnLabeling())
            .subscribeOn(Schedulers.computation())
    }
}