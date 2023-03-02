package com.example.ussdaplication.presentation.ui.main

import com.example.ussdaplication.BaseViewModel
import com.example.ussdaplication.domain.ads.GetNewsModel
import com.example.ussdaplication.domain.model.GetTypeModel
import com.example.ussdaplication.domain.model.internet.GetInternetModel
import com.example.ussdaplication.domain.model.minute.GetMinuteModel
import com.example.ussdaplication.domain.model.sms.GetSmsModel
import com.example.ussdaplication.domain.model.tarif.GetTariffModel
import com.example.ussdaplication.domain.model.ussd.GetUssdModel
import com.example.ussdaplication.domain.repository.MainRepository
import com.example.ussdaplication.presentation.common.UIListState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel() {

    fun getNews(company: String): Flow<UIListState<GetNewsModel>> {
        return getList { mainRepository.getNews(company) }
    }

    suspend fun getTypeI(): Flow<UIListState<GetTypeModel>> {
        return getList { mainRepository.getInternetType() }
    }

    suspend fun getInternet(id: String, company: String): Flow<UIListState<GetInternetModel>> {
        return getList { mainRepository.getInternet(id, company) }
    }

    suspend fun getTypeM(): Flow<UIListState<GetTypeModel>> {
        return getList { mainRepository.getMinuteType() }
    }

    suspend fun getMinute(id: String, company: String): Flow<UIListState<GetMinuteModel>> {
        return getList { mainRepository.getMinute(id, company) }
    }

    suspend fun getTypeS(): Flow<UIListState<GetTypeModel>> {
        return getList { mainRepository.getSmsType() }
    }

    suspend fun getSms(id: String, company: String): Flow<UIListState<GetSmsModel>> {
        return getList { mainRepository.getSms(id, company) }
    }

    fun getUssd(company: String): Flow<UIListState<GetUssdModel>> {
        return getList { mainRepository.getUssd(company) }
    }

    suspend fun getTariff(company: String): Flow<UIListState<GetTariffModel>> {
        return getList { mainRepository.getTariff(company) }
    }
}