package com.example.ussdaplication.domain.model.ussd

data class GetUssdModel(
    val _id: String,
    val code: String,
    val company: String,
    val name: String
)