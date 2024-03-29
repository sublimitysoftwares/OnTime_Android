package com.allocate.ontime.business_logic.di

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
import com.allocate.ontime.business_logic.repository.DeviceInfoRepository
import com.allocate.ontime.business_logic.utils.Constants
import com.allocate.ontime.business_logic.viewmodel.MainViewModel
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

    // It provides the dependency of DaoRepository Class.
    @Provides
    fun provideDeviceInfoDaoRepository(database: OnTimeDatabase): DeviceInfoDao {
        return database.deviceInfoDao()
    }
    @Singleton
    @Provides
    fun provideMainViewModel(repository: DeviceInfoRepository, @ApplicationContext context: Context): MainViewModel =
        MainViewModel(repository, context)

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

    @Singleton
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
            Constants.DEFAULT_AS_API_URL
        )
        return Retrofit.Builder()
            .baseUrl(asApiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
