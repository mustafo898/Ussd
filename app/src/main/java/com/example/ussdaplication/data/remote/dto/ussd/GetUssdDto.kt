package com.example.ussdaplication.data.remote.dto.ussd

data class GetUssdDto(
    val _id: String,
    val code: String,
    val company: String,
    val name: String
)