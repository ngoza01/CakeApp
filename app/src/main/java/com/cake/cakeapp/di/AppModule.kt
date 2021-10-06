package com.cake.cakeapp.di

import com.cake.cakeapp.service.CakeService
import com.cake.cakeapp.service.ServiceHelper
import com.cake.cakeapp.service.ServiceHelperImpl
import com.cake.cakeapp.BuildConfig
import com.cake.cakeapp.repository.CakeRepository
import com.cake.cakeapp.repository.CakeRepositoryImpl
import com.cake.cakeapp.utility.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideCakeService(retrofit: Retrofit) = retrofit.create(CakeService::class.java)

    @Provides
    @Singleton
    fun provideServiceHelper(cakeService: CakeService): ServiceHelper = ServiceHelperImpl(cakeService)

    @Provides
    @Singleton
    fun provideCakeRepository(serviceHelper: ServiceHelper): CakeRepository = CakeRepositoryImpl(serviceHelper)

}