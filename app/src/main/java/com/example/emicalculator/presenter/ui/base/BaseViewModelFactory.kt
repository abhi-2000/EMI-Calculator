package com.example.emicalculator.presenter.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.emicalculator.domain.usecase.BulkInsertToEmiTablesUseCase
import com.example.emicalculator.domain.usecase.InsertEmiUseCase
import com.example.emicalculator.domain.usecase.LoadAllEmiDataUseCase
import com.example.emicalculator.domain.usecase.LoadAllUpcomingEmiDataUseCase
import com.example.emicalculator.domain.usecase.UpdateUpComingEmiUseCase

class BaseViewModelFactory(
    private val bulkInsertToEmiTablesUseCase: BulkInsertToEmiTablesUseCase,
    private val insertEmiUseCase: InsertEmiUseCase,
    private val loadAllUpcomingEmiDataUseCase: LoadAllUpcomingEmiDataUseCase,
    private val loadAllEmiDataUseCase: LoadAllEmiDataUseCase,
    private val updateUpComingEmiUseCase: UpdateUpComingEmiUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BaseViewModel(
            bulkInsertToEmiTablesUseCase,
            insertEmiUseCase,
            loadAllUpcomingEmiDataUseCase,
            loadAllEmiDataUseCase,
            updateUpComingEmiUseCase
        ) as T
    }
}