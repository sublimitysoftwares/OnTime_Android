//package com.allocate.ontime.business_logic.network
//
//
//
//
//import com.allocate.ontime.business_logic.annotations.SuperAdminRetrofit
//import com.allocate.ontime.business_logic.utils.Constants
//import com.allocate.ontime.encryption.EDModel
//import retrofit2.Response
//import retrofit2.http.POST
//import retrofit2.http.Url
//import javax.inject.Singleton
//
//
//
//interface SuperAdminApi {
//    @POST(value = Constants.getCISuperAdminDetails)
//    suspend fun getCISuperAdminDetails(@Url url:String): Response<EDModel>
//}