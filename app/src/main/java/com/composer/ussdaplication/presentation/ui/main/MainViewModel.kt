package com.composer.ussdaplication.presentation.ui.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.composer.ussdaplication.BaseViewModel
import com.composer.ussdaplication.domain.ads.GetNewsModel
import com.composer.ussdaplication.domain.model.GetTypeModel
import com.composer.ussdaplication.domain.model.internet.GetInternetModel
import com.composer.ussdaplication.domain.model.minute.GetMinuteModel
import com.composer.ussdaplication.domain.model.sms.GetSmsModel
import com.composer.ussdaplication.domain.model.tarif.GetTariffModel
import com.composer.ussdaplication.domain.model.ussd.GetUssdModel
import com.composer.ussdaplication.domain.repository.MainRepository
import com.composer.ussdaplication.presentation.common.UIListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel() {

    fun getNews(company: String): Flow<UIListState<GetNewsModel>> {
        return getList { mainRepository.getNews(company) }
    }
//
//    suspend fun getTypeI(company: String,lang:String): Flow<UIListState<GetTypeModel>> {
//        return getList { mainRepository.getInternetType(company,lang) }
//    }
//
//    suspend fun getInternet(id: String, company: String,lang:String): Flow<UIListState<GetInternetModel>> {
//        return getList { mainRepository.getInternet(id, company,lang) }
//    }
//
//    suspend fun getTypeM(company: String,lang:String): Flow<UIListState<GetTypeModel>> {
//        return getList { mainRepository.getMinuteType(company,lang) }
//    }
//
//    suspend fun getMinute(id: String, company: String,lang:String): Flow<UIListState<GetMinuteModel>> {
//        return getList { mainRepository.getMinute(id, company,lang) }
//    }
//
//    suspend fun getTypeS(company: String,lang:String): Flow<UIListState<GetTypeModel>> {
//        return getList { mainRepository.getSmsType(company,lang) }
//    }
//
//    suspend fun getSms(id: String, company: String,lang:String): Flow<UIListState<GetSmsModel>> {
//        return getList { mainRepository.getSms(id, company,lang) }
//    }
//
//    fun getUssd(company: String,lang:String): Flow<UIListState<GetUssdModel>> {
//        return getList { mainRepository.getUssd(company,lang) }
//    }
//
//    suspend fun getTariff(company: String,lang:String): Flow<UIListState<GetTariffModel>> {
//        return getList { mainRepository.getTariff(company,lang) }
//    }
}