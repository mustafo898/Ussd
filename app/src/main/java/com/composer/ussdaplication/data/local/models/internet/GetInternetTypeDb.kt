package com.composer.ussdaplication.data.local.models.internet

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "internetTypeDb")
data class GetInternetTypeDb(
    @PrimaryKey
    val _id: String,
    val company: String,
    val createdAt: String,
    val nameUz: String,
    val nameRu: String,
    val nameEn: String,
    val nameId: String,
)