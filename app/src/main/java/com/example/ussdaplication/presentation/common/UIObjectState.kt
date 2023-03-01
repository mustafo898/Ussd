package com.example.ussdaplication.presentation.common

data class UIObjectState<T>(
    val error: String = "",
    val data: T? = null,
    val isLoading: Boolean = false
)