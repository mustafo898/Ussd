package com.example.ussdaplication.data.repository

import android.util.Log
import com.example.ussdaplication.data.common.ResponseHandler
import com.example.ussdaplication.data.local.dao.*
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

class MainRepositoryImpl @Inject constructor(
    private val mainApiService: MainApiService,
    private val internetDao: InternetDao,
    private val minuteDao: MinuteDao,
    private val smsDao: SmsDao,
    private val tariffDao: TariffDao,
    private val ussdDao: UssdDao,
) : MainRepository, ResponseHandler() {

    override suspend fun getInternetType(): Flow<Resource<List<GetTypeModel>>> =
        loadResultDao(internetDao.getInternetType(),
            { mainApiService.getInternetType() },
            { data, flow ->
                try {
                    internetDao.setInternetType(data)
                    Log.d("skdkfjshfjdh", "getInternetType: ${internetDao.getInternetType()}")
                    Log.d("sddsfsfsdf", "getInternetType: $data")

                    flow.emit(Resource.Success(data.map { it.toModel() }))
                } catch (e: Exception) {
                    flow.emit(Resource.Error(e.message.toString()))
                }
            })

    override suspend fun getInternet(
        id: String,
        company: String
    ): Flow<Resource<List<GetInternetModel>>> = loadResultDao(
        internetDao.getInternetIDList(id, company), {
            mainApiService.getInternet(id, company)
        }, { data, flow ->
            try {
                internetDao.setInternet(data)
                Log.d("skdkfjshfjdh", "getInternet: ${internetDao.getInternetIDList(id, company)}")
                Log.d("sddsfsfsdf", "getInternet: $data")

                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getSmsType(): Flow<Resource<List<GetTypeModel>>> =
        loadResultDao(smsDao.getSmsType(), {
            mainApiService.getSmsType()
        }, { data, flow ->
            try {
                smsDao.setSmsType(data)
                Log.d("skdkfjshfjdh", "getSmsType: ${smsDao.getSms()}")
                Log.d("sddsfsfsdf", "getSmsType: $data")

                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getSms(id: String, company: String): Flow<Resource<List<GetSmsModel>>> =
        loadResultDao(smsDao.getSmsByIDList(id, company), {
            mainApiService.getSms(id, company)
        }, { data, flow ->
            try {
                smsDao.setSms(data)
                Log.d("skdkfjshfjdh", "getSms: ${smsDao.getSmsByIDList(id, company)}")
                Log.d("sddsfsfsdf", "getSms: $data")

                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getMinuteType(): Flow<Resource<List<GetTypeModel>>> =
        loadResultDao(minuteDao.getMinuteType(), {
            mainApiService.getMinuteType()
        }, { data, flow ->
            try {
                minuteDao.setMinuteType(data)
                Log.d("skdkfjshfjdh", "getMinuteType: ${minuteDao.getMinuteType()}")
                Log.d("sddsfsfsdf", "getMinuteType: $data")

                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getMinute(
        id: String,
        company: String
    ): Flow<Resource<List<GetMinuteModel>>> =
        loadResultDao(minuteDao.getMinuteByIDList(id, company), {
            mainApiService.getMinute(id, company)
        }, { data, flow ->
            try {
                minuteDao.setMinute(data)
                Log.d("skdkfjshfjdh", "getMinute: ${minuteDao.getMinuteByIDList(id, company)}")
                Log.d("sddsfsfsdf", "getMinute: $data")
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
        loadResultDao(tariffDao.getTariff(), {
            mainApiService.getTariff(company)
        }, { data, flow ->
            try {
                tariffDao.setTariff(data)
                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getUssd(company: String): Flow<Resource<List<GetUssdModel>>> =
        loadResultDao(ussdDao.getUssd(), {
            mainApiService.getUssd(company)
        }, { data, flow ->
            try {
                ussdDao.setUssd(data)
                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })
}