package com.ezlo.testtask.api.model

import com.google.gson.annotations.SerializedName

data class DeviceApiModel (
    @SerializedName("PK_Device")
    val pkDevice: Int? = null,
    @SerializedName("MacAddress")
    val macAddress: String? = null,
    @SerializedName("PK_DeviceType")
    val pkDeviceType: Int? = null,
    @SerializedName("PK_DeviceSubType")
    val pkDeviceSubType: Int? = null,
    @SerializedName("Firmware")
    val firmware: String? = null,
    @SerializedName("Server_Device")
    val serverDevice: String? = null,
    @SerializedName("Server_Event")
    val serverEvent: String? = null,
    @SerializedName("Server_Account")
    val serverAccount: String? = null,
    @SerializedName("InternalIP")
    val internalApi: String? = null,
    @SerializedName("LastAliveReported")
    val lastAliveReported: String? = null,
    @SerializedName("Platform")
    val platform: String? = null
)