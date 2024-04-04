package com.ezlo.testtask.api

import com.ezlo.testtask.api.model.ListDevicesApiModel
import retrofit2.http.GET

interface VeramobileApi {
    companion object {
        const val URL = "https://veramobile.mios.com"
    }

    @GET("/test_android/items.test")
    suspend fun getDevices(): ListDevicesApiModel
}