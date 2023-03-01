package com.example.ussdaplication.domain.model.tarif

data class GetTariffModel(
    val _id: String,
    val code: String,
    val company: String,
    val duration: String,
    val internet: String,
    val minute: String,
    val name: String,
    val price: Int,
    val sms: String
)