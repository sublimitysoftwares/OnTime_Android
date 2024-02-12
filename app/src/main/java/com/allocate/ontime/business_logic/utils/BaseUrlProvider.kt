//package com.allocate.ontime.business_logic.utils
//
//import com.allocate.ontime.business_logic.network.DeviceInfoApi
//
//
//object BaseUrlProvider {
//    const val BASE_URL_1 = "http://cpaapi.sublimitysoft.com/"
//    const val BASE_URL_2 = "http://airstack.sublimitysoft.com/AirstackAPI/API/"
//
//    fun provideBaseUrl(apiService: Class<*>): String =
//        when (apiService) {
//            DeviceInfoApi::class.java -> BASE_URL_1
//            SuperAdminApi::class.java -> BASE_URL_2
//            else -> throw IllegalArgumentException("Unknown API service")
//        }
//}