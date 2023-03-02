package com.example.ussdaplication.data.remote.dto.sms

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "smsTypeDto")
data class GetSmsTypeDto(
    @PrimaryKey
    val _id: String,
    val createdAt: String,
    val name: String,
)