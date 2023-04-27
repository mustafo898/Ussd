package com.composer.ussdaplication.data.remote.dto.tarif

import com.composer.ussdaplication.data.remote.dto.LanguageDto

data class GetTariffDto(
    val _id: String,
    val checkLimit: String,
    val code: String,
    val company: String,
    val description: LanguageDto? = null,
    val internet: LanguageDto,
    val internetPrice: Int,
    val minute: LanguageDto,
    val minutePrice: Int,
    val name: LanguageDto,
    val outInternet: LanguageDto? = null,
    val outMinute: LanguageDto? = null,
    val price: Int,
    val sms: LanguageDto,
    val smsPrice: Int,
    val typeId:String
)