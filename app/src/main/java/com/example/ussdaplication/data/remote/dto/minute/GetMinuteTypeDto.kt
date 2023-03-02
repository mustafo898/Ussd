package com.example.ussdaplication.data.remote.dto.minute

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "minuteTypeDto")
data class GetMinuteTypeDto(
    @PrimaryKey
    val _id: String,
    val createdAt: String,
    val name: String
)