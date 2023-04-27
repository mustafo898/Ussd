package com.composer.ussdaplication.domain.model.tarif

import com.composer.ussdaplication.data.remote.dto.LanguageDto
import com.composer.ussdaplication.domain.model.LanguageModel

data class GetTariffModel(
    val _id: String,
    val checkLimit: String,
    val code: String,
    val company: String,
    val description: LanguageModel? = null,
    val internet: LanguageModel,
    val internetPrice: Int,
    val minute: LanguageModel,
    val minutePrice: Int,
    val name: LanguageModel,
    val outInternet: LanguageModel? = null,
    val outMinute: LanguageModel? = null,
    val price: Int,
    val sms: LanguageModel,
    val smsPrice: Int,
    val typeId:String
)