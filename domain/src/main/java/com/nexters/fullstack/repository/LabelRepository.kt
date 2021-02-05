package com.nexters.fullstack.repository

import com.nexters.fullstack.usecase.Label
import io.reactivex.Single

interface LabelRepository {
    fun load(): Single<List<Label>>
}