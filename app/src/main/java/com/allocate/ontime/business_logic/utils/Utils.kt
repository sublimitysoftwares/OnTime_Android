package com.allocate.ontime.business_logic.utils


import javax.inject.Singleton

@Singleton
object Utils {
    var imei: String? = null
    const val BASE_URL = "http://cpaapi.sublimitysoft.com/"
    var asApiURL: String = "http://airstack.sublimitysoft.com/AirstackAPI/API/"
}