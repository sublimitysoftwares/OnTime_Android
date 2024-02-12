//package com.allocate.ontime.business_logic.di
//
//
//
//import com.allocate.ontime.business_logic.annotations.DeviceInfoRetrofit
//import com.allocate.ontime.business_logic.annotations.SuperAdminRetrofit
//import com.allocate.ontime.business_logic.network.DeviceInfoApi
//import com.allocate.ontime.business_logic.network.SuperAdminApi
//import com.allocate.ontime.business_logic.repository.DeviceInfoRepository
//import com.allocate.ontime.business_logic.repository.SuperAdminRepository
//import com.allocate.ontime.business_logic.utils.Utils
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ViewModelComponent
//import dagger.hilt.components.SingletonComponent
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.create
//import javax.inject.Singleton
//
//
//@Module
//@InstallIn(ViewModelComponent::class)
//object ViewModelModule {
//
////    @Provides
////    fun provideSuperAdminRepository(@SuperAdminRetrofit retrofit1:Retrofit) : SuperAdminRepository {
////        return SuperAdminRepository(retrofit1.create(SuperAdminApi::class.java))
////    }
////
////    @Provides
////    fun provideDeviceInfoRepository(@DeviceInfoRetrofit retrofit2:Retrofit) : DeviceInfoRepository {
////        return DeviceInfoRepository(retrofit2.create(DeviceInfoApi::class.java))
////    }
//
////    @Singleton
////    @Provides
////    @SuperAdminRetrofit
////    fun provideSuperAdminApi(@SuperAdminRetrofit retrofit1: Retrofit): SuperAdminApi {
////        return retrofit1.create(SuperAdminApi::class.java)
////    }
////
////    @Provides
////    @SuperAdminRetrofit
////    fun provideRetrofitClient2(): Retrofit {
////        return Utils.asApiURL.let {
////            Retrofit.Builder()
////                .baseUrl(it)
////                .addConverterFactory(GsonConverterFactory.create())
////                .build()
////        }
////    }
//
//}