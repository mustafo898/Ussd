package com.example.ussdaplication.data.remote.dto.minute

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "minute")
data class GetMinuteDto(
    @PrimaryKey
    val _id: String,
    val code: String,
    val company: String,
    val price: Int,
    val typeId: String
)