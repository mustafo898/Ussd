package com.composer.ussdaplication.di.module

import android.content.Context
import androidx.room.Room
import com.composer.ussdaplication.data.local.dao.*
import com.composer.ussdaplication.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "ussd_application")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideInternetDao(appDatabase: AppDatabase): InternetDao = appDatabase.internetDao()

    @Provides
    @Singleton
    fun provideMinuteDao(appDatabase: AppDatabase): MinuteDao = appDatabase.minuteDao()

    @Provides
    @Singleton
    fun provideSmsDao(appDatabase: AppDatabase): SmsDao = appDatabase.smsDao()

    @Provides
    @Singleton
    fun provideTariffDao(appDatabase: AppDatabase): TariffDao = appDatabase.tariffDao()

    @Provides
    @Singleton
    fun provideUssdDao(appDatabase: AppDatabase): UssdDao = appDatabase.ussdDao()
}