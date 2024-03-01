package com.allocate.ontime.business_logic.network


import com.allocate.ontime.business_logic.utils.Constants
import com.allocate.ontime.encryption.EDModel
import retrofit2.Response
import retrofit2.http.POST

interface SuperAdminApi {
    @POST(value = Constants.GET_CI_SUPER_ADMIN_DETAILS)
    suspend fun getCISuperAdminDetails(): Response<EDModel>
}