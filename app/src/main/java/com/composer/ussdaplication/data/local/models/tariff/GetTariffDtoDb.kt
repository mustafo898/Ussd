package com.composer.ussdaplication.data.local.models.tariff

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tariff")
data class GetTariffDtoDb(
    @PrimaryKey
    val _id: String,
    val checkLimit: String,
    val code: String,
    val company: String,
    val descriptionId: String,
    val descriptionUz: String,
    val descriptionRu: String,
    val descriptionEn: String,
    val internetId: String,
    val internetUz: String,
    val internetRu: String,
    val internetEn: String,
    val internetPrice: Int,
    val minuteId: String,
    val minuteUz: String,
    val minuteRu: String,
    val minuteEn: String,
    val minutePrice: Int,
    val nameId: String,
    val nameUz: String,
    val nameRu: String,
    val nameEn: String,
    val outInternetId: String,
    val outInternetUz: String,
    val outInternetRu: String,
    val outInternetEn: String,
    val outMinuteId: String,
    val outMinuteUz: String,
    val outMinuteRu: String,
    val outMinuteEn: String,
    val price: Int,
    val smsId: String,
    val smsUz: String,
    val smsRu: String,
    val smsEn: String,
    val smsPrice: Int,    val typeId:String
)