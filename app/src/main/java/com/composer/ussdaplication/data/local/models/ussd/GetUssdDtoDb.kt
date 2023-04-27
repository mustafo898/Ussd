package com.composer.ussdaplication.data.local.models.ussd

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.composer.ussdaplication.App
import com.composer.ussdaplication.data.remote.dto.LanguageDto

@Entity(tableName = "ussd")
data class GetUssdDtoDb(
    @PrimaryKey
    val _id: String,
    val code: String,
    val company: String,
    val descriptionId: String,
    val descriptionUz: String,
    val descriptionRu: String,
    val descriptionEn: String,
    val nameId: String,
    val nameUz: String,
    val nameRu: String,
    val nameEn: String
)