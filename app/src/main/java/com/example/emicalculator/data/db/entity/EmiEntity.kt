package com.example.emicalculator.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date

@Entity(tableName = "emi_table")
data class EmiEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val principalAmount: String,
    val interestRate: String,
    val tenureInMonths: String,
    val emiAmount: String,
    val date: String
)
