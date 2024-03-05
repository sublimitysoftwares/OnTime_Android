package com.allocate.ontime.business_logic.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.allocate.ontime.BuildConfig
import com.allocate.ontime.business_logic.annotations.DeviceInfoRetrofit
import com.allocate.ontime.business_logic.annotations.SuperAdminRetrofit
import com.allocate.ontime.business_logic.data.room.DeviceInfoDao
import com.allocate.ontime.business_logic.data.room.OnTimeDatabase
import com.allocate.ontime.business_logic.data.shared_preferences.SecureSharedPrefs
import com.allocate.ontime.business_logic.network.DeviceInfoApi
import com.allocate.ontime.business_logic.network.SuperAdminApi
import com.allocate.ontime.business_logic.repository.DaoRepository
import com.allocate.ontime.business_logic.repository.DeviceInfoRepository
import com.allocate.ontime.business_logic.utils.Constants
import com.allocate.ontime.business_logic.utils.DeviceUtils
import com.allocate.ontime.business_logic.viewmodel.MainViewModel
import com.allocate.ontime.business_logic.viewmodel.splash.SplashViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // It provides the dependency of OnTimeRepository Class.
    @Provides
    fun provideDeviceInfoRepository(
        deviceInfoApi: DeviceInfoApi,
        superAdminApi: SuperAdminApi,
        deviceUtils: DeviceUtils,
    ): DeviceInfoRepository =
        DeviceInfoRepository(deviceInfoApi, superAdminApi, deviceUtils)

    @Provides
    fun provideSlashViewModelContext(
        deviceInfoRepository: DeviceInfoRepository,
        daoRepository: DaoRepository,
        deviceUtils: DeviceUtils,
        @ApplicationContext context: Context,
    ) =
        SplashViewModel(deviceInfoRepository, daoRepository, deviceUtils, context)

    @Provides
    fun provideMainViewModelContext(
        deviceInfoRepository: DeviceInfoRepository,
        @ApplicationContext context: Context,
    ) =
        MainViewModel(deviceInfoRepository, context)

    @Singleton
    @Provides
    fun provideDeviceUtils(
        @ApplicationContext context: Context,
    ) =
        DeviceUtils(context)

    // It provides the dependency of DaoRepository Class.
    @Provides
    fun provideDeviceInfoDaoRepository(database: OnTimeDatabase): DeviceInfoDao {
        return database.deviceInfoDao()
    }

    // It provides the dependency of OnTimeDatabase Class.
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): OnTimeDatabase =
        Room.databaseBuilder(context, OnTimeDatabase::class.java, name = Constants.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    //     It provides the dependency of OnTimeApi Interface.
    @Singleton
    @Provides
    fun provideDeviceInfoApi(@DeviceInfoRetrofit retrofit: Retrofit): DeviceInfoApi {
        return retrofit.create(DeviceInfoApi::class.java)
    }

    @Provides
    fun provideSuperAdminApi(@SuperAdminRetrofit retrofit: Retrofit): SuperAdminApi {
        return retrofit.create(SuperAdminApi::class.java)
    }

    @Provides
    @DeviceInfoRetrofit
    fun provideRetrofitClient1(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @SuperAdminRetrofit
    fun provideRetrofitClient2(@ApplicationContext context: Context): Retrofit {
        val asApiUrl = SecureSharedPrefs(context).getData(
            Constants.AS_API_URL,
            "http://airstack.sublimitysoft.com/AirstackAPI/API/"
        )
        return Retrofit.Builder()
            .baseUrl(asApiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}