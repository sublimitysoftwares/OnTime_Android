package com.example.ontime_jitendra.di

import com.example.ontime_jitendra.network.OnTimeApi
import com.example.ontime_jitendra.repository.OnTimeRepository
import com.example.ontime_jitendra.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideOnTimeRepository(api: OnTimeApi) = OnTimeRepository(api)

    @Singleton
    @Provides
    fun provideOnTimeApi(): OnTimeApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OnTimeApi::class.java)
    }
}