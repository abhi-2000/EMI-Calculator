package com.example.emicalculator.domain.usecase

import com.example.emicalculator.data.db.entity.EmiEntity
import com.example.emicalculator.data.db.entity.UpComingEmiEntity
import com.example.emicalculator.domain.repository.Repository

class BulkInsertToEmiTablesUseCase(private val repository: Repository) {

    suspend fun execute(upcomingEmiList: List<UpComingEmiEntity>)  = repository.bulkInsertToEmiTables(upcomingEmiList)

}