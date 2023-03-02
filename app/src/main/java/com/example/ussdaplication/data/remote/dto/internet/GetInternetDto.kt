package com.example.ussdaplication.data.remote.dto.internet

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "internet")
data class GetInternetDto(
    @PrimaryKey
    val _id: String,
    val code: String,
    val company: String,
    val duration: String,
    val name: String,
    val price: Int,
    val typeId: String
)