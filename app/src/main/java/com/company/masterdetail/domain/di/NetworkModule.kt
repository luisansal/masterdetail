package com.company.masterdetail.domain.di

import com.company.masterdetail.BuildConfig
import com.company.masterdetail.domain.remote.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BuildConfig.API_BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }

    @Singleton
    @Provides
    fun providePokeApiClient(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }
}

