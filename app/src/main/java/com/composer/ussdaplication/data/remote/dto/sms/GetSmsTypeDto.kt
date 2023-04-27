package com.composer.ussdaplication.data.remote.dto.sms

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.composer.ussdaplication.data.remote.dto.LanguageDto

data class GetSmsTypeDto(
    val _id: String,
    val company: String,
    val createdAt: String,
    val name: LanguageDto
)