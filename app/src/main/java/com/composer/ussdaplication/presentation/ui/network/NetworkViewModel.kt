package com.composer.ussdaplication.presentation.ui.network

import com.composer.ussdaplication.BaseViewModel
import com.composer.ussdaplication.domain.model.GetTypeModel
import com.composer.ussdaplication.domain.model.internet.GetInternetModel
import com.composer.ussdaplication.domain.repository.MainRepository
import com.composer.ussdaplication.presentation.common.UIListState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NetworkViewModel @Inject constructor(private val mainRepository: MainRepository) : BaseViewModel() {

    suspend fun getType(company: String): Flow<UIListState<GetTypeModel>> {
        return getList { mainRepository.getInternetType(company) }
    }

    suspend fun getInternet(id: String, company: String): Flow<UIListState<GetInternetModel>> {
        return getList { mainRepository.getInternet(id, company) }
    }
}