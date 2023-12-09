package com.example.emicalculator.data.repository

import com.example.emicalculator.data.db.dao.EmiDao
import com.example.emicalculator.data.db.dao.UpComingEmiDao
import com.example.emicalculator.data.db.entity.EmiEntity
import com.example.emicalculator.data.db.entity.UpComingEmiEntity

class LocalDataSourceImpl(private val emiDao: EmiDao, private val upComingEmiDao: UpComingEmiDao) : LocalDataSource {


    override suspend fun loadAllEmiData(): List<EmiEntity> {
        return emiDao.getAllEmi()
    }

    override suspend fun loadUpComingEmi(emiId: Long): List<UpComingEmiEntity> {
        return upComingEmiDao.getAllUpComingEmi(emiId)
    }

    override suspend fun updateUpComingEmi(id: Long, isPaid: Boolean): Int {
        return upComingEmiDao.updateUpComingEmi(id, isPaid)
    }

    override suspend fun bulkInsertToEmiTables(allUpComingEmi: List<UpComingEmiEntity>) {
        upComingEmiDao.bulkInsertToEmiTables(allUpComingEmi)
    }

    override suspend fun insertEmi(emiEntity: EmiEntity):Long {
        return emiDao.insertEmi(emiEntity)
    }


}