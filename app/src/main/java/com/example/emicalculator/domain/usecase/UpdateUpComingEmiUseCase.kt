package com.example.emicalculator.domain.usecase

import com.example.emicalculator.domain.repository.Repository

class UpdateUpComingEmiUseCase(private val repository: Repository) {

    suspend fun execute(id: Long, isPaid: Boolean) = repository.updateUpComingEmi(id, isPaid)

}