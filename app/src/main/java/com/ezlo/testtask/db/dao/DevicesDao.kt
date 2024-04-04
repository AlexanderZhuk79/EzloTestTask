package com.ezlo.testtask.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.ezlo.testtask.db.model.DeviceDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface DevicesDao: BaseDao<DeviceDbModel> {
    @Query("SELECT * FROM DeviceDbModel order by pkDevice")
    fun getAllDevices() : Flow<List<DeviceDbModel>>

    @Query("SELECT * FROM DeviceDbModel WHERE pkDevice = :pkDevice")
    fun getDevice(pkDevice: Int) : DeviceDbModel

    @Transaction
    fun refresh(newList: List<DeviceDbModel>){
        deleteAll()
        insert(newList)
    }

    @Query("DELETE FROM DeviceDbModel")
    fun deleteAll()

}