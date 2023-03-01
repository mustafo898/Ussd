package com.example.ussdaplication.data.remote.dto.internet

data class GetInternetDto(
    val _id: String,
    val code: String,
    val company: String,
    val duration: String,
    val name: String,
    val price: Int,
    val typeId: String
)