package com.composer.ussdaplication.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.composer.ussdaplication.data.local.dao.*
import com.composer.ussdaplication.data.local.models.internet.GetInternetDb
import com.composer.ussdaplication.data.local.models.internet.GetInternetTypeDb
import com.composer.ussdaplication.data.local.models.minute.GetMinuteDb
import com.composer.ussdaplication.data.local.models.minute.GetMinuteTypeDb
import com.composer.ussdaplication.data.local.models.sms.GetSmsDb
import com.composer.ussdaplication.data.local.models.sms.GetSmsTypeDb
import com.composer.ussdaplication.data.local.models.tariff.GetTariffDtoDb
import com.composer.ussdaplication.data.local.models.tariff.GetTariffTypeDtoDb
import com.composer.ussdaplication.data.local.models.ussd.GetUssdDtoDb

@Database(
    entities = [GetInternetTypeDb::class, GetSmsTypeDb::class, GetMinuteTypeDb::class, GetInternetDb::class, GetSmsDb::class, GetMinuteDb::class, GetUssdDtoDb::class, GetTariffDtoDb::class, GetTariffTypeDtoDb::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun internetDao(): InternetDao
    abstract fun minuteDao(): MinuteDao
    abstract fun smsDao(): SmsDao
    abstract fun ussdDao(): UssdDao
    abstract fun tariffDao(): TariffDao
}