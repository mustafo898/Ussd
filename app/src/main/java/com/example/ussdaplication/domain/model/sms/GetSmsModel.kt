package com.example.ussdaplication.domain.model.sms

data class GetSmsModel(
    val _id: String,
    val code: String,
    val company: String,
    val duration: String,
    val name: String,
    val price: Int,
    val typeId: String
)