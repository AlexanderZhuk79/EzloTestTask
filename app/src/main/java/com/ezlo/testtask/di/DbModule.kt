package com.ezlo.testtask.di

import android.app.Application
import androidx.room.Room
import com.ezlo.testtask.db.RoomAppDatabase
import com.ezlo.testtask.db.dao.DevicesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {
    @Provides
    @Singleton
    fun provideRoomAppDatabase(
        application: Application
    ): RoomAppDatabase {
        val builder = Room
            .databaseBuilder(application, RoomAppDatabase::class.java, "AppDatabase.db")
            .fallbackToDestructiveMigration()

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideDevicesDao(appDatabase: RoomAppDatabase): DevicesDao {
        return appDatabase.devicesDao()
    }
}