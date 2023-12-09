package com.example.emicalculator.presenter.di.core

import android.app.Application
import androidx.room.Room
import com.example.emicalculator.data.db.dao.EmiDao
import com.example.emicalculator.data.db.database.EmiDatabase
import com.example.emicalculator.data.db.dao.UpComingEmiDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideLocalDatabase(context:Application): EmiDatabase {
        return Room.databaseBuilder(context=context, EmiDatabase::class.java,"EmiDb").fallbackToDestructiveMigration().build()
   }

  @Singleton
  @Provides
  fun provideEmiDao(emiDatabase: EmiDatabase): EmiDao {
      return emiDatabase.emiDao()
  }
    @Singleton
    @Provides
    fun provideUpComingEmiDao(emiDatabase: EmiDatabase): UpComingEmiDao {
        return emiDatabase.upComingEmiDao()
    }

}