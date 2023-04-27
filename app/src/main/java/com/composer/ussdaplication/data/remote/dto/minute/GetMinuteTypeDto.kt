package com.composer.ussdaplication.data.remote.dto.minute

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.composer.ussdaplication.data.remote.dto.LanguageDto

data class GetMinuteTypeDto(
    val _id: String,
    val company: String,
    val createdAt: String,
    val name: LanguageDto
)