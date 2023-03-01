package com.example.ussdaplication.presentation.ui.network

import com.example.ussdaplication.BaseViewModel
import com.example.ussdaplication.domain.ads.GetNewsModel
import com.example.ussdaplication.domain.model.GetTypeModel
import com.example.ussdaplication.domain.model.internet.GetInternetModel
import com.example.ussdaplication.domain.repository.MainRepository
import com.example.ussdaplication.presentation.common.UIListState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NetworkViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel() {

    suspend fun getType(): Flow<UIListState<GetTypeModel>> {
        return getList { mainRepository.getInternetType() }
    }

    suspend fun getInternet(id: String, company: String): Flow<UIListState<GetInternetModel>> {
        return getList { mainRepository.getInternet(id, company) }
    }

    suspend fun getNews(company: String): Flow<UIListState<GetNewsModel>> {
        return getList { mainRepository.getNews(company) }
    }
}