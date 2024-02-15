package com.allocate.ontime.business_logic.di

import android.content.Context
import androidx.room.Room
import com.allocate.ontime.business_logic.annotations.DeviceInfoRetrofit
import com.allocate.ontime.business_logic.annotations.SuperAdminRetrofit
import com.allocate.ontime.business_logic.data.room.DeviceInfoDao
import com.allocate.ontime.business_logic.data.room.OnTimeDatabase
import com.allocate.ontime.business_logic.network.DeviceInfoApi
import com.allocate.ontime.business_logic.network.SuperAdminApi
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
    fun provideDeviceInfoRepository(@DeviceInfoRetrofit deviceInfoApi: DeviceInfoApi,@SuperAdminRetrofit superAdminApi: SuperAdminApi): DeviceInfoRepository =
        DeviceInfoRepository(deviceInfoApi,superAdminApi)

    @Provides
    @DeviceInfoRetrofit
    fun provideSlashViewModelContext(
        deviceInfoRepository: DeviceInfoRepository,
        daoRepository: DaoRepository,
        @ApplicationContext context: Context,
    ) =
        SplashViewModel(deviceInfoRepository, daoRepository, context)

    // It provides the dependency of DaoRepository Class.
    @Provides
    @DeviceInfoRetrofit
    fun provideDeviceInfoDaoRepository(database: OnTimeDatabase): DeviceInfoDao {
        return database.deviceInfoDao()
    }

    // It provides the dependency of OnTimeDatabase Class.
    @Singleton
    @Provides
    @DeviceInfoRetrofit
    fun provideAppDatabase(@ApplicationContext context: Context): OnTimeDatabase =
        Room.databaseBuilder(context, OnTimeDatabase::class.java, name = Constants.databaseName)
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
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @SuperAdminRetrofit
    fun provideRetrofitClient2(): Retrofit {
        return Utils.asApiURL.let {
            Retrofit.Builder()
                .baseUrl(it)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}