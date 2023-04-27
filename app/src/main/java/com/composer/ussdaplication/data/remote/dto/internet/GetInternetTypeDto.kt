package com.composer.ussdaplication.data.remote.dto.internet

import com.composer.ussdaplication.data.remote.dto.LanguageDto

data class GetInternetTypeDto(
    val _id: String,
    val company: String,
    val createdAt: String,
    val name: LanguageDto
)