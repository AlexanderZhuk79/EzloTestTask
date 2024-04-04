package com.ezlo.testtask.repository

import com.ezlo.testtask.api.VeramobileApi
import com.ezlo.testtask.db.dao.DevicesDao
import com.ezlo.testtask.db.model.DeviceDbModel
import com.ezlo.testtask.mappers.apiDeviceModelToDbDevice
import javax.inject.Inject

class VeramobileRepository @Inject constructor(private val api: VeramobileApi, private val devicesDao: DevicesDao)  {
    suspend fun refresh(){
        val newListDevices = api.getDevices().devices.sortedBy { it.pkDevice }.map { apiDeviceModelToDbDevice(it) }
        newListDevices.forEachIndexed { index, deviceDbModel -> deviceDbModel.deviceName = "Home Number ${index+1}" }
        devicesDao.refresh(newListDevices)
    }

    fun getDevicesList() = devicesDao.getAllDevices()

    fun saveDevice(deviceModel: DeviceDbModel) = devicesDao.update(deviceModel)

    fun deleteDevice(deviceModel: DeviceDbModel) = devicesDao.delete(deviceModel)
}