package com.ezlo.testtask.api.model

import com.google.gson.annotations.SerializedName

data class ListDevicesApiModel (
    @SerializedName("Devices")
    val devices: List<DeviceApiModel> = emptyList()
)