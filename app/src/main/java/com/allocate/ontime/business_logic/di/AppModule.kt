package com.allocate.ontime.business_logic.di


import com.allocate.ontime.business_logic.autoback_navigation_manager.AutoBackNavigationManager
import android.content.Context
import androidx.activity.OnBackPressedDispatcher
import androidx.navigation.NavController
import androidx.room.Room
import com.allocate.ontime.business_logic.data.room.DeviceInfoDao
import com.allocate.ontime.business_logic.data.room.OnTimeDatabase
import com.allocate.ontime.business_logic.network.OnTimeApi
import com.allocate.ontime.business_logic.repository.DaoRepository
import com.allocate.ontime.business_logic.repository.OnTimeRepository
import com.allocate.ontime.business_logic.utils.Constants
import com.allocate.ontime.business_logic.viewmodel.admin.AdminViewModel
import com.allocate.ontime.business_logic.viewmodel.splash.SplashViewModel
import com.allocate.ontime.business_logic.viewmodel.super_admin.SuperAdminSettingViewModel
import com.allocate.ontime.presentation_logic.navigation.HomeScreenRoot
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // It provides the dependency of OnTimeRepository Class.
    @Provides
    fun provideOnTimeRepository(api: OnTimeApi) =
        OnTimeRepository(api)

    @Provides
    fun provideSplashViewModel(repository: OnTimeRepository, daoRepository: DaoRepository) =
        SplashViewModel(repository, daoRepository)

    @Provides
    @Singleton
    fun provideAutoBackNavigationManager(): AutoBackNavigationManager {
        return AutoBackNavigationManager()
    }

    @Provides
    fun provideSuperAdminSettingViewModel(
        repository: OnTimeRepository,
        autoBackNavigationManager: AutoBackNavigationManager
    ): SuperAdminSettingViewModel {
        return SuperAdminSettingViewModel(repository, autoBackNavigationManager)
    }


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
