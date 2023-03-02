package com.example.ussdaplication.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ussdaplication.data.local.dao.*
import com.example.ussdaplication.data.remote.dto.internet.GetInternetDto
import com.example.ussdaplication.data.remote.dto.internet.GetInternetTypeDto
import com.example.ussdaplication.data.remote.dto.minute.GetMinuteDto
import com.example.ussdaplication.data.remote.dto.minute.GetMinuteTypeDto
import com.example.ussdaplication.data.remote.dto.sms.GetSmsDto
import com.example.ussdaplication.data.remote.dto.sms.GetSmsTypeDto
import com.example.ussdaplication.data.remote.dto.tarif.GetTariffDto
import com.example.ussdaplication.data.remote.dto.ussd.GetUssdDto

@Database(
    entities = [GetInternetTypeDto::class, GetSmsTypeDto::class, GetMinuteTypeDto::class, GetInternetDto::class, GetSmsDto::class, GetMinuteDto::class, GetUssdDto::class, GetTariffDto::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun internetDao(): InternetDao
    abstract fun minuteDao(): MinuteDao
    abstract fun smsDao(): SmsDao
    abstract fun ussdDao(): UssdDao
    abstract fun tariffDao(): TariffDao
}