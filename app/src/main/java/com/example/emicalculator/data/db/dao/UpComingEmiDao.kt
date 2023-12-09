package com.example.emicalculator.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.emicalculator.data.db.entity.UpComingEmiEntity


@Dao
interface UpComingEmiDao {

    @Insert
    suspend fun bulkInsertToEmiTables(allUpComingEmi:List<UpComingEmiEntity>)

    @Query("SELECT * FROM upComingEmi where emiId =:emiId")
    suspend fun getAllUpComingEmi(emiId:Long): List<UpComingEmiEntity>

    @Query("UPDATE upComingEmi SET isPaid =:isPaid WHERE id =:id")
    suspend fun updateUpComingEmi(id: Long, isPaid: Boolean): Int



}