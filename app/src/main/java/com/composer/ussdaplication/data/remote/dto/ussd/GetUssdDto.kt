package com.composer.ussdaplication.data.remote.dto.ussd

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.composer.ussdaplication.App
import com.composer.ussdaplication.data.remote.dto.LanguageDto

@Entity(tableName = "ussd")
data class GetUssdDto(
    @PrimaryKey
    val _id: String,
    val code: String,
    val company: String,
    val description: LanguageDto,
    val name: LanguageDto
)