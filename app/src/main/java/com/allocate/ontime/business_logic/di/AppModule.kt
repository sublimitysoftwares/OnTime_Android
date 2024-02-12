package com.allocate.ontime.business_logic.di

import android.content.Context
import androidx.room.Room
import com.allocate.ontime.business_logic.annotations.DeviceInfoRetrofit
import com.allocate.ontime.business_logic.annotations.SuperAdminRetrofit
import com.allocate.ontime.business_logic.data.room.DeviceInfoDao
import com.allocate.ontime.business_logic.data.room.OnTimeDatabase
import com.allocate.ontime.business_logic.network.DeviceInfoApi
import com.allocate.ontime.business_logic.repository.DaoRepository
import com.allocate.ontime.business_logic.repository.DeviceInfoRepository

import com.allocate.ontime.business_logic.utils.Constants
import com.allocate.ontime.business_logic.utils.Utils
import com.allocate.ontime.business_logic.viewmodel.splash.SplashViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // It provides the dependency of OnTimeRepository Class.
    @Provides
    fun provideDeviceInfoRepository(api: DeviceInfoApi): DeviceInfoRepository =
        DeviceInfoRepository(api)

    @Provides
    fun provideSlashViewModelContext(
        deviceInfoRepository: DeviceInfoRepository,
        daoRepository: DaoRepository,
        @ApplicationContext context: Context,
    ) =
        SplashViewModel(deviceInfoRepository, daoRepository, context)

    // It provides the dependency of DaoRepository Class.
    @Provides
    fun provideDeviceInfoDaoRepository(database: OnTimeDatabase): DeviceInfoDao {
        return database.deviceInfoDao()
    }

    // It provides the dependency of OnTimeDatabase Class.
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): OnTimeDatabase =
        Room.databaseBuilder(context, OnTimeDatabase::class.java, name = Constants.databaseName)
            .fallbackToDestructiveMigration()
            .build()


//     It provides the dependency of OnTimeApi Interface.
    @Singleton
    @Provides
    fun provideDeviceInfoApi(retrofit: Retrofit): DeviceInfoApi {
        return retrofit.create(DeviceInfoApi::class.java)
    }


//    @Provides
//    fun provideSuperAdminApi(retrofit: Retrofit): SuperAdminApi {
//        return retrofit.create(SuperAdminApi::class.java)
//    }
//
//    @Provides
//    fun provideSuperAdminRepository(api: SuperAdminApi): SuperAdminRepository =
//        SuperAdminRepository(api)


//    @Provides
//    @DeviceInfoRetrofit
//    fun provideRetrofitClient(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(Constants.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }




    class BaseUrlInterceptor() : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()

            // Read the appropriate base URL from shared preferences (or other source)
            val baseUrl = when (request.tag()) {
                // Use different keys for different APIs
                "BASE_URL" -> "http://cpaapi.sublimitysoft.com/"
                "AS_API_URL" -> "http://airstack.sublimitysoft.com/AirstackAPI/API/"
                else -> throw IllegalArgumentException("Unknown API tag")
            }

            // Build a new request with the modified base URL
            val newUrl =
                request.url().newBuilder().scheme(request.url().scheme()).host(baseUrl).build()
            val newRequest = request.newBuilder().url(newUrl).build()

            return chain.proceed(newRequest)
        }
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(BaseUrlInterceptor())
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .build()
    }
}