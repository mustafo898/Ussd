package com.example.ussdaplication.domain.repository

import com.example.ussdaplication.domain.ads.GetNewsModel
import com.example.ussdaplication.domain.model.GetTypeModel
import com.example.ussdaplication.domain.model.internet.GetInternetModel
import com.example.ussdaplication.domain.model.minute.GetMinuteModel
import com.example.ussdaplication.domain.model.sms.GetSmsModel
import com.example.ussdaplication.domain.model.tarif.GetTariffModel
import com.example.ussdaplication.domain.model.ussd.GetUssdModel
import kotlinx.coroutines.flow.Flow
import uz.rounded.baqlajon.domain.common.Resource

interface MainRepository {
    suspend fun getInternetType(): Flow<Resource<List<GetTypeModel>>>
    suspend fun getInternet(id: String, company: String): Flow<Resource<List<GetInternetModel>>>

    suspend fun getSmsType(): Flow<Resource<List<GetTypeModel>>>
    suspend fun getSms(id: String, company: String): Flow<Resource<List<GetSmsModel>>>

    suspend fun getMinuteType(): Flow<Resource<List<GetTypeModel>>>
    suspend fun getMinute(id: String, company: String): Flow<Resource<List<GetMinuteModel>>>

    suspend fun getNews(company: String): Flow<Resource<List<GetNewsModel>>>
    suspend fun getTariff(company: String): Flow<Resource<List<GetTariffModel>>>
    suspend fun getUssd(company: String): Flow<Resource<List<GetUssdModel>>>
}