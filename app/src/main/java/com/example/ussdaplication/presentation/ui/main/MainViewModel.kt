package com.example.ussdaplication.presentation.ui.main

import com.example.ussdaplication.BaseViewModel
import com.example.ussdaplication.domain.ads.GetNewsModel
import com.example.ussdaplication.domain.repository.MainRepository
import com.example.ussdaplication.presentation.common.UIListState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : BaseViewModel() {

    fun getNews(company: String): Flow<UIListState<GetNewsModel>> {
        return getList { mainRepository.getNews(company) }
    }

}