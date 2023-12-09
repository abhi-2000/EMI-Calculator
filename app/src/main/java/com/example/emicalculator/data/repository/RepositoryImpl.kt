package com.example.emicalculator.data.repository

import com.example.emicalculator.data.db.entity.EmiEntity
import com.example.emicalculator.data.db.entity.UpComingEmiEntity
import com.example.emicalculator.domain.repository.Repository

class RepositoryImpl(private val localDataSource: LocalDataSource):Repository {


    override suspend fun loadAllEmiData(): List<EmiEntity> {
       return localDataSource.loadAllEmiData()
    }

    override suspend fun loadUpComingEmi(emiId: Long): List<UpComingEmiEntity> {
       return localDataSource.loadUpComingEmi(emiId)
    }

    override suspend fun updateUpComingEmi(id: Long, isPaid: Boolean): Int {
     return localDataSource.updateUpComingEmi(id,isPaid)
    }

    override suspend fun bulkInsertToEmiTables(allUpComingEmi: List<UpComingEmiEntity>) {
       return localDataSource.bulkInsertToEmiTables(allUpComingEmi)
    }

    override suspend fun insertEmi(emiEntity: EmiEntity):Long {
        return localDataSource.insertEmi(emiEntity)
    }

}