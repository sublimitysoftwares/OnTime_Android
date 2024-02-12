//package com.allocate.ontime.business_logic.repository
//
//
//
//import android.util.Log
//import com.allocate.ontime.business_logic.annotations.SuperAdminRetrofit
//import com.allocate.ontime.business_logic.data.DataOrException
//import com.allocate.ontime.business_logic.network.SuperAdminApi
//import com.allocate.ontime.encryption.EDModel
//import retrofit2.Response
//import javax.inject.Inject
//
//class SuperAdminRepository @Inject constructor (
// private val api: SuperAdminApi
//) {
//    suspend fun postSuperAdminDetails(): DataOrException<Response<EDModel>, Exception> {
//        val dataOrException = DataOrException<Response<EDModel>, Exception>()
//        try {
//            dataOrException.data = api.getCISuperAdminDetails()
//            Log.d("SuperAdmin","${dataOrException.data}")
//        } catch (exception: Exception) {
//            dataOrException.e = exception
//            Log.d("EXC", "getSuperAdminDetails: ${dataOrException.e}")
//        }
//        return dataOrException
//    }
//}