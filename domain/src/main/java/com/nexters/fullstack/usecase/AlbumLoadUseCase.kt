package com.nexters.fullstack.usecase

import com.nexters.fullstack.repository.AlbumRepository
import com.nexters.fullstack.source.data.LocalImageDomain
import com.nexters.fullstack.usecase.base.BaseUseCase
import io.reactivex.Single

internal class AlbumLoadUseCase(private val albumRepository: AlbumRepository) :
    BaseUseCase<String, Single<List<LocalImageDomain>?>> {
    override fun buildUseCase(params: String): Single<List<LocalImageDomain>?> {
        return Single.just(albumRepository.getUnLabeling(params))
//            .subscribeOn(Schedulers.computation())
    }
}