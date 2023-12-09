package com.example.emicalculator.presenter.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emicalculator.data.db.entity.EmiEntity
import com.example.emicalculator.data.db.entity.UpComingEmiEntity
import com.example.emicalculator.domain.usecase.BulkInsertToEmiTablesUseCase
import com.example.emicalculator.domain.usecase.InsertEmiUseCase
import com.example.emicalculator.domain.usecase.LoadAllEmiDataUseCase
import com.example.emicalculator.domain.usecase.LoadAllUpcomingEmiDataUseCase
import com.example.emicalculator.domain.usecase.UpdateUpComingEmiUseCase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.pow

class BaseViewModel(
    private val bulkInsertToEmiTablesUseCase: BulkInsertToEmiTablesUseCase,
    private val insertEmiUseCase: InsertEmiUseCase,
    private val loadAllUpcomingEmiDataUseCase: LoadAllUpcomingEmiDataUseCase,
    private val loadAllEmiDataUseCase: LoadAllEmiDataUseCase,
    private val updateUpComingEmiUseCase: UpdateUpComingEmiUseCase
) : ViewModel() {


    fun loadAllUpcomingData(emiId: Long, callBack: ((List<UpComingEmiEntity>) -> Unit)? = null) =
        viewModelScope.launch {
            callBack?.invoke(loadAllUpcomingEmiDataUseCase.execute(emiId))
        }


    fun loadAllEmiData(callBack: ((List<EmiEntity>) -> Unit)? = null) = viewModelScope.launch {
        callBack?.invoke(loadAllEmiDataUseCase.execute())
    }

    fun insertEmiData(emiEntity: EmiEntity,callBack: ((Long) -> Unit)? = null) = viewModelScope.launch {
        callBack?.invoke(insertEmiUseCase.execute(emiEntity))
    }


    fun bulkInsertEmiData(upcomingEmiList: List<UpComingEmiEntity>) = viewModelScope.launch {
        bulkInsertToEmiTablesUseCase.execute(upcomingEmiList)
    }


    fun updateUpcomingEmiData(id: Long, isPaid: Boolean) = viewModelScope.launch {
        updateUpComingEmiUseCase.execute(id, isPaid)
    }

    fun calculateEMI(
        principal: Double,
        annualInterestRate: Double,
        loanTermInYears: Int
    ): Double {
        val monthlyInterestRate = annualInterestRate / 12.0 / 100.0
        val numberOfPayments = loanTermInYears.toDouble()

        return (principal * monthlyInterestRate * (1 + monthlyInterestRate).pow(numberOfPayments)) /
                ((1 + monthlyInterestRate).pow(numberOfPayments) - 1)
    }


    fun generateUpComingEMIPayments(
        startDate: String,
        tenureInMonths: Int,
        emi:String,
        emiId:Long
    ): ArrayList<UpComingEmiEntity> {
        val tempList = arrayListOf<UpComingEmiEntity>()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        // Parse the provided start date
        calendar.time = dateFormat.parse(startDate) ?: Date()
        // Calculate and add payment months based on tenure
        for (i in 1..tenureInMonths) {
            tempList.add(
                UpComingEmiEntity(
                    0,
                    dateFormat.format(calendar.time).toString(),
                    emiId,
                    false,
                    emi
                )
            )
            calendar.add(Calendar.MONTH, 1) // Add one month for the next payment
        }
        return tempList
    }


}