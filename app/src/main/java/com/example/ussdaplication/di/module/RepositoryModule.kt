package com.example.ussdaplication.di.module

import com.example.ussdaplication.data.remote.MainApiService
import com.example.ussdaplication.data.repository.MainRepositoryImpl
import com.example.ussdaplication.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(mainApiService: MainApiService): MainRepository {
        return MainRepositoryImpl(mainApiService)
    }
}