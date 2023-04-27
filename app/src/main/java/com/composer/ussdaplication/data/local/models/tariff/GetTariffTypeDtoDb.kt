package com.composer.ussdaplication.data.local.models.tariff

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tariff_type")
data class GetTariffTypeDtoDb(
    @PrimaryKey
    val _id: String,
    val company: String,
    val createdAt: String,
    val nameUz: String,
    val nameRu: String,
    val nameEn: String,
    val nameId: String,
)