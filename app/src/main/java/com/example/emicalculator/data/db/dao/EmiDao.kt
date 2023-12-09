package com.example.emicalculator.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.emicalculator.data.db.entity.EmiEntity

@Dao
interface EmiDao {

    @Insert
    suspend fun insertEmi(emiEntity: EmiEntity):Long

    @Query("SELECT * FROM emi_table order by id desc")
    suspend fun getAllEmi(): List<EmiEntity>

    @Update
    suspend fun updateItem(emiEntity: EmiEntity)
}
