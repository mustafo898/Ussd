package com.composer.ussdaplication.data.local.convertors

import androidx.room.TypeConverter
import com.composer.ussdaplication.data.remote.dto.LanguageDto

class Convertors {
    @TypeConverter
    fun fromIdLanguage(data: LanguageDto): String = data.toString()

    @TypeConverter
    fun toLanguage(id: String): LanguageDto {
        return LanguageDto(id, id, id, id)
    }
}