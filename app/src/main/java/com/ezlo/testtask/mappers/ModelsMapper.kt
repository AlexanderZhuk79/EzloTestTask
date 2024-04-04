package com.ezlo.testtask.mappers

import com.ezlo.testtask.api.model.DeviceApiModel
import com.ezlo.testtask.db.model.DeviceDbModel


fun apiDeviceModelToDbDevice(apiModel: DeviceApiModel): DeviceDbModel {
    return DeviceDbModel().apply {
        pkDevice = apiModel.pkDevice?:0
        macAddress = apiModel.macAddress
        pkDeviceType = apiModel.pkDeviceType
        pkDeviceSubType = apiModel.pkDeviceSubType
        firmware = apiModel.firmware
        serverDevice = apiModel.serverDevice
        serverEvent = apiModel.serverEvent
        serverAccount = apiModel.serverAccount
        internalApi = apiModel.internalApi
        lastAliveReported = apiModel.lastAliveReported
        platform = apiModel.platform
    }
}