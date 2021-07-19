package com.nexters.fullstack.usecase

import com.nexters.fullstack.repository.LabelRepository
import com.nexters.fullstack.source.local.DomainUserLabel
import com.nexters.fullstack.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow

//class LabelSearchUseCase(private val repository: LabelRepository, private val keyword : String) : BaseUseCase<LabelRepository, Flow<List<DomainUserLabel>>> {
//    override fun buildUseCase(params: LabelRepository): Flow<List<DomainUserLabel>> {
//
//    }
//
//    fun loadLabels() : Flow<List<DomainUserLabel>>{
//        // TODO : return
//
//    }
//
//    fun filterByKeyword(){
//        // TODO : 로직 구상해둔 것 구현
//    }
//}