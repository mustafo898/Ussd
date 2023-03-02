package com.example.ussdaplication.data.remote.dto.tarif

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tariff")
data class GetTariffDto(
    @PrimaryKey
    val _id: String,
    val code: String,
    val company: String,
    val duration: String,
    val internet: String,
    val minute: String,
    val name: String,
    val price: Int,
    val sms: String
)