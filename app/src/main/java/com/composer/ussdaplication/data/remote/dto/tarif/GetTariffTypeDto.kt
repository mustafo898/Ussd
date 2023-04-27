package com.composer.ussdaplication.data.remote.dto.tarif

import com.composer.ussdaplication.data.remote.dto.LanguageDto

data class GetTariffTypeDto(
    val _id: String,
    val company: String,
    val createdAt: String,
    val name: LanguageDto
)