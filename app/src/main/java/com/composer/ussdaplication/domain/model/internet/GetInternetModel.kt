package com.composer.ussdaplication.domain.model.internet

import com.composer.ussdaplication.domain.model.LanguageModel

data class GetInternetModel(
    val _id: String,
    val company: String,
    val description: LanguageModel? = null,
    val duration: LanguageModel,
    val name: LanguageModel,
    val price: Int,
    val turnOff: String,
    val turnOn: String,
    val typeId: String,
    val checkLimit:String
)