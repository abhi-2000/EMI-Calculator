package com.example.emicalculator.data.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.emicalculator.data.db.dao.EmiDao
import com.example.emicalculator.data.db.dao.UpComingEmiDao
import com.example.emicalculator.data.db.entity.EmiEntity
import com.example.emicalculator.data.db.entity.UpComingEmiEntity

@Database(entities = [EmiEntity::class,UpComingEmiEntity::class], version = 2, exportSchema = false)
abstract class EmiDatabase : RoomDatabase() {

    abstract fun emiDao(): EmiDao
    abstract fun upComingEmiDao():UpComingEmiDao

}
