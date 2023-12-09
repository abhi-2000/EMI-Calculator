package com.example.emicalculator.domain.usecase

import com.example.emicalculator.data.db.entity.EmiEntity
import com.example.emicalculator.domain.repository.Repository

class InsertEmiUseCase(private val repository: Repository) {

    suspend fun execute(emiEntity: EmiEntity):Long = repository.insertEmi(emiEntity)

}