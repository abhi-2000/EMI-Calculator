package com.example.emicalculator.domain.usecase

import com.example.emicalculator.data.db.entity.UpComingEmiEntity
import com.example.emicalculator.domain.repository.Repository

class LoadAllUpcomingEmiDataUseCase(private val repository: Repository) {

    suspend fun execute(emiId: Long):List<UpComingEmiEntity>  = repository.loadUpComingEmi(emiId)

}