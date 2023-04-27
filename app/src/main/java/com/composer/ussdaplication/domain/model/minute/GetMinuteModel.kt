package com.composer.ussdaplication.domain.model.minute

import com.composer.ussdaplication.domain.model.LanguageModel

data class GetMinuteModel(
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