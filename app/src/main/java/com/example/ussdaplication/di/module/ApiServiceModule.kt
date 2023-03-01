package com.example.ussdaplication.di.module

import com.example.ussdaplication.data.remote.MainApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiServiceModule {

    @Singleton
    @Provides
    fun provideMainService(retrofit: Retrofit): MainApiService =
        retrofit.create(MainApiService::class.java)
}