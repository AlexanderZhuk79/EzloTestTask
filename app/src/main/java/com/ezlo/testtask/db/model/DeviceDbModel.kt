package com.ezlo.testtask.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Entity
@Parcelize
class DeviceDbModel: Parcelable {

    @SerializedName("PK_Device")
    @PrimaryKey
    var pkDevice: Int = 0
    @SerializedName("MacAddress")
    var macAddress: String? = null
    @SerializedName("PK_DeviceType")
    var pkDeviceType: Int? = null
    @SerializedName("PK_DeviceSubType")
    var pkDeviceSubType: Int? = null
    @SerializedName("Firmware")
    var firmware: String? = null
    @SerializedName("Server_Device")
    var serverDevice: String? = null
    @SerializedName("Server_Event")
    var serverEvent: String? = null
    @SerializedName("Server_Account")
    var serverAccount: String? = null
    @SerializedName("InternalIP")
    var internalApi: String? = null
    @SerializedName("LastAliveReported")
    var lastAliveReported: String? = null
    @SerializedName("Platform")
    var platform: String? = null
    @SerializedName("DeviceName")
    var deviceName: String? = null

}