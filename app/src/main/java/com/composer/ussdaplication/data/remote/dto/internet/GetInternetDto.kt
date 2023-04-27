package com.composer.ussdaplication.data.remote.dto.internet

import com.composer.ussdaplication.data.remote.dto.LanguageDto

data class GetInternetDto(
    val _id: String,
    val company: String,
    val description: LanguageDto? = null,
    val duration: LanguageDto,
    val name: LanguageDto,
    val price: Int,
    val turnOff: String,
    val turnOn: String,
    val typeId: String,
    val checkLimit:String
)