package com.example.emicalculator.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random


@Entity(tableName = "upComingEmi")
data class UpComingEmiEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long=0 ,
    val monthDate: String,
    var emiId: Long,
    var isPaid: Boolean,
    var emi:String
)
