package com.example.ussdaplication.data.remote.dto.ussd

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ussd")
data class GetUssdDto(
    @PrimaryKey
    val _id: String,
    val code: String,
    val company: String,
    val name: String
)