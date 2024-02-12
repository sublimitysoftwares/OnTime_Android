package com.allocate.ontime.encryption

import com.google.gson.annotations.SerializedName

data class EDModel(@SerializedName("data") var data: String) {
    fun <T> getResponseModel(clazz: Class<T>): T {
        return EDHelper.decrypt(data, clazz)
    }
}