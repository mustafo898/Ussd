package com.composer.ussdaplication.data.local.models.minute

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.composer.ussdaplication.data.remote.dto.LanguageDto

@Entity(tableName = "minute")
data class GetMinuteDb(
    @PrimaryKey
    val _id: String,
    val company: String,
    val descriptionEn: String,
    val descriptionRu: String,
    val descriptionUz: String,
    val durationEn: String,
    val durationUz: String,
    val durationRu: String,
    val nameUz:String,
    val nameRu:String,
    val nameEn:String,
    val durationId: String,
    val descriptionId: String,
    val nameId:String,
    val price: Int,
    val turnOff: String,
    val turnOn: String,
    val typeId: String,
    val checkLimit:String
)