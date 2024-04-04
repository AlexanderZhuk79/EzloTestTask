package com.ezlo.testtask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ezlo.testtask.db.dao.DevicesDao
import com.ezlo.testtask.db.model.DeviceDbModel

@Database(
    entities = [DeviceDbModel::class],
    version = 1,
    exportSchema = true
)

abstract class RoomAppDatabase : RoomDatabase() {
    abstract fun devicesDao(): DevicesDao
}