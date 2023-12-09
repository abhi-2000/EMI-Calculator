package com.example.emicalculator.domain.repository

import com.example.emicalculator.data.db.entity.EmiEntity
import com.example.emicalculator.data.db.entity.UpComingEmiEntity

interface  Repository {


    suspend fun loadAllEmiData():List<EmiEntity>

    suspend fun loadUpComingEmi(emiId: Long):List<UpComingEmiEntity>

    suspend fun updateUpComingEmi(id:Long,isPaid:Boolean):Int

    suspend fun bulkInsertToEmiTables(allUpComingEmi:List<UpComingEmiEntity>)

    suspend fun insertEmi(emiEntity: EmiEntity):Long

}