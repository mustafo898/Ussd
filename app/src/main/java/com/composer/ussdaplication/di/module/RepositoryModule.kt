package com.composer.ussdaplication.di.module

import com.composer.ussdaplication.data.local.dao.*
import com.composer.ussdaplication.data.remote.MainApiService
import com.composer.ussdaplication.data.repository.MainRepositoryImpl
import com.composer.ussdaplication.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        mainApiService: MainApiService,
        internetDao: InternetDao,
        smsDao: SmsDao,
        ussdDao: UssdDao,
        tariffDao: TariffDao,
        minuteDao: MinuteDao
    ): MainRepository {
        return MainRepositoryImpl(
            mainApiService,
            internetDao,
            minuteDao,
            smsDao,
            tariffDao,
            ussdDao
        )
    }
}