package com.example.emicalculator.presenter.di.modules

import com.example.emicalculator.data.db.dao.EmiDao
import com.example.emicalculator.data.db.dao.UpComingEmiDao
import com.example.emicalculator.data.repository.LocalDataSource
import com.example.emicalculator.data.repository.LocalDataSourceImpl
import com.example.emicalculator.data.repository.RepositoryImpl
import com.example.emicalculator.domain.repository.Repository
import com.example.emicalculator.domain.usecase.BulkInsertToEmiTablesUseCase
import com.example.emicalculator.domain.usecase.InsertEmiUseCase
import com.example.emicalculator.domain.usecase.LoadAllEmiDataUseCase
import com.example.emicalculator.domain.usecase.LoadAllUpcomingEmiDataUseCase
import com.example.emicalculator.domain.usecase.UpdateUpComingEmiUseCase
import com.example.emicalculator.presenter.ui.base.BaseViewModelFactory
import com.example.emicalculator.presenter.ui.landing.LandingScreenAdapter
import com.example.emicalculator.presenter.ui.upcoming.UpComingEmiAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Modules {

    @Singleton
    @Provides
    fun provideRepository(localDataSource: LocalDataSource): Repository {
        return RepositoryImpl(localDataSource)
    }

    @Singleton
    @Provides
    fun provideDataSource(emiDao: EmiDao, upComingEmiDao: UpComingEmiDao): LocalDataSource {
        return LocalDataSourceImpl(emiDao, upComingEmiDao)
    }


    @Singleton
    @Provides
    fun provideBulkInsertToEmiTablesUseCase(repository: Repository): BulkInsertToEmiTablesUseCase {
        return BulkInsertToEmiTablesUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideInsertEmiUseCase(repository: Repository): InsertEmiUseCase {
        return InsertEmiUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideLoadAllEmiDataUseCase(repository: Repository): LoadAllEmiDataUseCase {
        return LoadAllEmiDataUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideLoadAllUpcomingEmiDataUseCase(repository: Repository): LoadAllUpcomingEmiDataUseCase {
        return LoadAllUpcomingEmiDataUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideUpdateUpComingEmiUseCase(repository: Repository): UpdateUpComingEmiUseCase {
        return UpdateUpComingEmiUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideLandingScreenAdapterUseCase(): LandingScreenAdapter {
        return LandingScreenAdapter()
    }

    @Singleton
    @Provides
    fun provideUpComingEmiAdapterUseCase(): UpComingEmiAdapter {
        return UpComingEmiAdapter()
    }

    @Provides
    fun baseViewModelFactoryProvider(
        bulkInsertToEmiTablesUseCase: BulkInsertToEmiTablesUseCase,
        insertEmiUseCase: InsertEmiUseCase,
        loadAllUpcomingEmiDataUseCase: LoadAllUpcomingEmiDataUseCase,
        loadAllEmiDataUseCase: LoadAllEmiDataUseCase,
        updateUpComingEmiUseCase: UpdateUpComingEmiUseCase
    ): BaseViewModelFactory {
        return BaseViewModelFactory(
            bulkInsertToEmiTablesUseCase,
            insertEmiUseCase,
            loadAllUpcomingEmiDataUseCase,
            loadAllEmiDataUseCase,
            updateUpComingEmiUseCase
        )
    }

}