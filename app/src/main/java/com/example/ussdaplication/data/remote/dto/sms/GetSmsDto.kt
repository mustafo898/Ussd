package com.example.ussdaplication.data.remote.dto.sms

data class GetSmsDto(
    val _id: String,
    val code: String,
    val company: String,
    val duration: String,
    val name: String,
    val price: Int,
    val typeId: String
)