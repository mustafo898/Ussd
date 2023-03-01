package com.example.ussdaplication.domain.model.minute

data class GetMinuteModel(
    val _id: String,
    val code: String,
    val company: String,
    val price: Int,
    val typeId: String
)