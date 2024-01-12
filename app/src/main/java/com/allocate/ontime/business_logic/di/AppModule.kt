package com.allocate.ontime.business_logic.di

import android.content.Context
import androidx.room.Room
import com.allocate.ontime.business_logic.data.room.OnTimeDatabase
import com.allocate.ontime.business_logic.data.room.OnTimeDatabaseDao
import com.allocate.ontime.business_logic.network.OnTimeApi
import com.allocate.ontime.business_logic.repository.OnTimeRepository
import com.allocate.ontime.business_logic.utils.Constants
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
    fun provideOnTimeRepository(api: OnTimeApi) =
        OnTimeRepository(api)

    // It provides the dependency of DaoRepository Class.
    @Provides
    fun provideDaoRepository(database: OnTimeDatabase): OnTimeDatabaseDao {
        return database.onTimeDatabaseDao()
    }

    // It provides the dependency of OnTimeDatabase Class.
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): OnTimeDatabase =
        Room.databaseBuilder(context, OnTimeDatabase::class.java, name = Constants.databaseName)
            .fallbackToDestructiveMigration()
            .build()

    // It provides the dependency of OnTimeApi Interface.
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