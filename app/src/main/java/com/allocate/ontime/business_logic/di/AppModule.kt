package com.allocate.ontime.business_logic.di

import com.allocate.ontime.business_logic.network.OnTimeApi
import com.allocate.ontime.business_logic.repository.OnTimeRepository
import com.allocate.ontime.business_logic.utils.Constants
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
    fun provideOnTimeApi(retrofit: Retrofit): OnTimeApi {
        return retrofit.create(OnTimeApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}