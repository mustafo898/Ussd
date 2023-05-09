package com.composer.ussdaplication.domain.repository

import com.composer.ussdaplication.domain.ads.GetNewsModel
import com.composer.ussdaplication.domain.common.Resource
import com.composer.ussdaplication.domain.model.GetTypeModel
import com.composer.ussdaplication.domain.model.internet.GetInternetModel
import com.composer.ussdaplication.domain.model.minute.GetMinuteModel
import com.composer.ussdaplication.domain.model.sms.GetSmsModel
import com.composer.ussdaplication.domain.model.tarif.GetTariffModel
import com.composer.ussdaplication.domain.model.ussd.GetUssdModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getInternetType(company: String): Flow<Resource<List<GetTypeModel>>>
    suspend fun getInternetTypeDb(company: String): Flow<Resource<List<GetTypeModel>>>

    suspend fun getInternet(id: String, company: String): Flow<Resource<List<GetInternetModel>>>
    suspend fun getInternetDb(id: String, company: String): Flow<Resource<List<GetInternetModel>>>

    suspend fun getSmsType(company: String): Flow<Resource<List<GetTypeModel>>>

    suspend fun getSms(id: String, company: String): Flow<Resource<List<GetSmsModel>>>

    suspend fun getMinuteType(company: String): Flow<Resource<List<GetTypeModel>>>

    suspend fun getMinute(id: String, company: String): Flow<Resource<List<GetMinuteModel>>>

    suspend fun getNews(company: String): Flow<Resource<List<GetNewsModel>>>

    suspend fun getTariffType(company: String): Flow<Resource<List<GetTypeModel>>>

    suspend fun getTariff(company: String, id: String): Flow<Resource<List<GetTariffModel>>>

    suspend fun getUssd(company: String): Flow<Resource<List<GetUssdModel>>>
}