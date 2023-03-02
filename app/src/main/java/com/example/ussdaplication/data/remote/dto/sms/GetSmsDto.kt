package com.example.ussdaplication.data.remote.dto.sms

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sms")
data class GetSmsDto(
    @PrimaryKey
    val _id: String,
    val code: String,
    val company: String,
    val duration: String,
    val name: String,
    val price: Int,
    val typeId: String
)