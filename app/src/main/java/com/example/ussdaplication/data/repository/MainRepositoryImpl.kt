package com.example.ussdaplication.data.repository

import com.example.ussdaplication.data.common.ResponseHandler
import com.example.ussdaplication.data.mapper.toModel
import com.example.ussdaplication.data.remote.MainApiService
import com.example.ussdaplication.domain.ads.GetNewsModel
import com.example.ussdaplication.domain.model.GetTypeModel
import com.example.ussdaplication.domain.model.internet.GetInternetModel
import com.example.ussdaplication.domain.model.minute.GetMinuteModel
import com.example.ussdaplication.domain.model.sms.GetSmsModel
import com.example.ussdaplication.domain.model.tarif.GetTariffModel
import com.example.ussdaplication.domain.model.ussd.GetUssdModel
import com.example.ussdaplication.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import uz.rounded.baqlajon.domain.common.Resource
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val mainApiService: MainApiService) :
    MainRepository, ResponseHandler() {

    override suspend fun getInternetType(): Flow<Resource<List<GetTypeModel>>> = loadResult({
        mainApiService.getInternetType()
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.map { it.toModel() }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun getInternet(
        id: String,
        company: String
    ): Flow<Resource<List<GetInternetModel>>> = loadResult({
        mainApiService.getInternet(id, company)
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.map { it.toModel() }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun getSmsType(): Flow<Resource<List<GetTypeModel>>> = loadResult({
        mainApiService.getSmsType()
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.map { it.toModel() }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun getSms(id: String, company: String): Flow<Resource<List<GetSmsModel>>> =
        loadResult({
            mainApiService.getSms(id, company)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getMinuteType(): Flow<Resource<List<GetTypeModel>>> = loadResult({
        mainApiService.getMinuteType()
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.map { it.toModel() }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun getMinute(
        id: String,
        company: String
    ): Flow<Resource<List<GetMinuteModel>>> =
        loadResult({
            mainApiService.getMinute(id, company)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getNews(company: String): Flow<Resource<List<GetNewsModel>>> =
        loadResult({
            mainApiService.getNews(company)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getTariff(company: String): Flow<Resource<List<GetTariffModel>>> =
        loadResult({
            mainApiService.getTariff(company)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getUssd(company: String): Flow<Resource<List<GetUssdModel>>> =
        loadResult({
            mainApiService.getUssd(company)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })
}