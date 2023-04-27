package com.composer.ussdaplication.domain.model.ussd

import com.composer.ussdaplication.domain.model.LanguageModel

data class GetUssdModel(
    val _id: String,
    val code: String,
    val company: String,
    val description: LanguageModel,
    val name: LanguageModel
)