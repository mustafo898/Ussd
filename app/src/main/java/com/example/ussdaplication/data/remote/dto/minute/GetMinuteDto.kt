package com.example.ussdaplication.data.remote.dto.minute

data class GetMinuteDto(
    val _id: String,
    val code: String,
    val company: String,
    val price: Int,
    val typeId: String
)