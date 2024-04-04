package com.ezlo.testtask.mappers

import com.ezlo.testtask.R

fun getImageForPlatform(platform: String?): Int {
    return when(platform) {
        "Sercomm G450" -> R.mipmap.vera_plus_big
        "Sercomm G550" -> R.mipmap.vera_secure_big
        else -> R.mipmap.vera_edge_big

    }
}