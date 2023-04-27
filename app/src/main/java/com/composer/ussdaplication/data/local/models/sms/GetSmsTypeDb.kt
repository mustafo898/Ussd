package com.composer.ussdaplication.data.local.models.sms

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.composer.ussdaplication.data.remote.dto.LanguageDto

@Entity(tableName = "smsTypeDto")
data class GetSmsTypeDb(
    @PrimaryKey
    val _id: String,
    val company: String,
    val createdAt: String,
    val nameUz:String,
    val nameRu:String,
    val nameEn:String,
    val nameId:String,
)