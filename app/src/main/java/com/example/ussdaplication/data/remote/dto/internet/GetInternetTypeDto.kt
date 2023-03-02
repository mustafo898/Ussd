package com.example.ussdaplication.data.remote.dto.internet

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "internetTypeDto")
data class GetInternetTypeDto(
    @PrimaryKey
    val _id: String,
    val createdAt: String,
    val name: String
)