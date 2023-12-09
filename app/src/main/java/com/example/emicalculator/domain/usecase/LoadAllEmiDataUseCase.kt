package com.example.emicalculator.domain.usecase

import com.example.emicalculator.data.db.entity.EmiEntity
import com.example.emicalculator.domain.repository.Repository

class LoadAllEmiDataUseCase(private val repository: Repository) {

    suspend fun execute():List<EmiEntity>  = repository.loadAllEmiData()

}