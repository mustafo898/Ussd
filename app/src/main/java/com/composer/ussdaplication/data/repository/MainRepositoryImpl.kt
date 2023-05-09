package com.composer.ussdaplication.data.repository

import android.util.Log
import com.composer.ussdaplication.data.common.ResponseHandler
import com.composer.ussdaplication.data.local.dao.*
import com.composer.ussdaplication.data.mapper.toDbModel
import com.composer.ussdaplication.data.mapper.toModel
import com.composer.ussdaplication.data.remote.MainApiService
import com.composer.ussdaplication.domain.ads.GetNewsModel
import com.composer.ussdaplication.domain.common.Resource
import com.composer.ussdaplication.domain.model.GetTypeModel
import com.composer.ussdaplication.domain.model.internet.GetInternetModel
import com.composer.ussdaplication.domain.model.minute.GetMinuteModel
import com.composer.ussdaplication.domain.model.sms.GetSmsModel
import com.composer.ussdaplication.domain.model.tarif.GetTariffModel
import com.composer.ussdaplication.domain.model.ussd.GetUssdModel
import com.composer.ussdaplication.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainApiService: MainApiService,
    private val internetDao: InternetDao,
    private val minuteDao: MinuteDao,
    private val smsDao: SmsDao,
    private val tariffDao: TariffDao,
    private val ussdDao: UssdDao,
) : MainRepository, ResponseHandler() {

    override suspend fun getInternetType(
        company: String
    ): Flow<Resource<List<GetTypeModel>>> = flow {

        emit(Resource.Success(internetDao.getInternetType(company).map { it.toModel() }))

        try {
            val response = mainApiService.getInternetType(company)

            if (response.isSuccessful) {
                if (response.body() != null) {
                    val result = response.body()
                    result?.data?.let { p ->

                        emit(
                            Resource.Success(internetDao.getInternetType(company)
                                .map { it.toModel() })
                        )

                        internetDao.deleteAllInternetType(company)
                        internetDao.setInternetType(p.map { it.toDbModel() })

                        Log.d("loggggggg", "getInternetType: ${
                            internetDao.getInternetType(company).map { it.toModel() }
                        }")

                        emit(
                            Resource.Success(internetDao.getInternetType(company)
                                .map { it.toModel() })
                        )
                    }
                } else {
                    emit(
                        Resource.Error(
                            "An expected error occurred! Data is null"
                        )
                    )
                }
            } else {
                emit(
                    Resource.Error(
                        "Something went wrong ${response.code()}"
                    )
                )
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    "An expected error occurred!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    "Couldn't reach server. Please check your internet connection!"
                )
            )
        }
    }

    override suspend fun getInternetTypeDb(company: String): Flow<Resource<List<GetTypeModel>>> =
        loadResult({ mainApiService.getInternetType(company) }, { data, flow ->

            internetDao.deleteAllInternetType(company)
            internetDao.setInternetType(data.map { it.toDbModel() })

            Log.d(
                "impl____1",
                "getInternetTypeDb --> 2: ${internetDao.getInternetType(company).map { it.toModel() }}"
            )

            flow.emit(Resource.Success(data.map { it.toModel() }))
        }, { flow ->
            Log.d(
                "impl____1",
                "getInternetTypeDb --> 1: ${internetDao.getInternetType(company).map { it.toModel() }}"
            )
            flow.emit(Resource.Success(internetDao.getInternetType(company).map { it.toModel() }))
        })

    override suspend fun getInternet(
        id: String, company: String
    ): Flow<Resource<List<GetInternetModel>>> = flow {

        emit(Resource.Success(internetDao.getInternetIDList(id, company).map { it.toModel() }))

        try {
            val response = mainApiService.getInternet(id, company)

            if (response.isSuccessful) {
                if (response.body() != null) {
                    val result = response.body()
                    result?.data?.let { p ->

                        internetDao.deleteAllInternet(id, company)
                        internetDao.setInternet(p.map { it.toDbModel() })
                        emit(
                            Resource.Success(
                                internetDao.getInternetIDList(id, company).map { it.toModel() })
                        )

                        Log.d(
                            "sdfshrfubsbfsdf", "getInternet: $id \n ${
                                internetDao.getInternetIDList(id, company).first().typeId
                            }"
                        )
                    }
                } else {
                    emit(
                        Resource.Error(
                            "An expected error occurred! Data is null"
                        )
                    )
                }
            } else {
                emit(
                    Resource.Error(
                        "Something went wrong ${response.code()}"
                    )
                )
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    "An expected error occurred!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    "Couldn't reach server. Please check your internet connection!"
                )
            )
        }
    }

    override suspend fun getInternetDb(
        id: String, company: String
    ): Flow<Resource<List<GetInternetModel>>> =
        loadResult({ mainApiService.getInternet(id, company) }, { data, flow ->

            internetDao.deleteAllInternet(id, company)
            internetDao.setInternet(data.map { it.toDbModel() })

            Log.d("impl____", "getInternetTypeDb: ${
                internetDao.getInternetIDList(id, company).map { it.toModel() }
            }")

            flow.emit(Resource.Success(data.map { it.toModel() }))
        }, { flow ->
            Log.d("impl____", "getInternetTypeDb: ${
                internetDao.getInternetIDList(id, company).map { it.toModel() }
            }")
            flow.emit(
                Resource.Success(internetDao.getInternetIDList(id, company).map { it.toModel() })
            )
        })

    override suspend fun getSmsType(
        company: String
    ): Flow<Resource<List<GetTypeModel>>> =
        loadResult({ mainApiService.getSmsType(company) }, { data, flow ->

            smsDao.deleteAllSmsType(company)
            smsDao.setSmsType(data.map { it.toDbModel() })

            Log.d("impl____", "getSmsType: ${
                smsDao.getSmsType(company).map { it.toModel() }
            }")

            flow.emit(Resource.Success(data.map { it.toModel() }))
        }, { flow ->
            Log.d("impl____", "getSmsType: ${
                smsDao.getSmsType(company).map { it.toModel() }
            }")
            flow.emit(
                Resource.Success(smsDao.getSmsType(company).map { it.toModel() })
            )
        })

    override suspend fun getSms(
        id: String, company: String
    ): Flow<Resource<List<GetSmsModel>>> =
        loadResult({ mainApiService.getSms(id, company) }, { data, flow ->

            smsDao.deleteAllSms(id, company)
            smsDao.setSms(data.map { it.toDbModel() })

            Log.d("impl____", "getSms: ${
                smsDao.getSmsByIDList(id, company).map { it.toModel() }
            }")

            flow.emit(Resource.Success(data.map { it.toModel() }))
        }, { flow ->
            Log.d("impl____", "getSms: ${
                smsDao.getSmsByIDList(id, company).map { it.toModel() }
            }")
            flow.emit(
                Resource.Success(smsDao.getSmsByIDList(id, company).map { it.toModel() })
            )
        })

    override suspend fun getMinuteType(
        company: String
    ): Flow<Resource<List<GetTypeModel>>> =
        loadResult({ mainApiService.getMinuteType(company) }, { data, flow ->

            minuteDao.deleteMinuteType(company)
            minuteDao.setMinuteType(data.map { it.toDbModel() })

            Log.d("impl____", "getMinuteType: ${
                minuteDao.getMinuteType(company).map { it.toModel() }
            }")

            flow.emit(Resource.Success(data.map { it.toModel() }))
        }, { flow ->
            Log.d("impl____", "getMinuteType: ${
                minuteDao.getMinuteType(company).map { it.toModel() }
            }")
            flow.emit(
                Resource.Success(minuteDao.getMinuteType(company).map { it.toModel() })
            )
        })

//    override suspend fun getMinuteType(
//        company: String
//    ): Flow<Resource<List<GetTypeModel>>> = flow {
//
//        emit(Resource.Success(minuteDao.getMinuteType(company).map { it.toModel() }))
//
//
//        try {
//            val response = mainApiService.getMinuteType(company)
//
//            if (response.isSuccessful) {
//                if (response.body() != null) {
//                    val result = response.body()
//                    result?.data?.let { p ->
//
//                        minuteDao.deleteAllMinuteType()
//                        minuteDao.setMinuteType(p.map { it.toDbModel() })
//
//                        emit(
//                            Resource.Success(minuteDao.getMinuteType(company).map { it.toModel() })
//                        )
//                    }
//                } else {
//                    emit(
//                        Resource.Error(
//                            "An expected error occurred! Data is null"
//                        )
//                    )
//                }
//            } else {
//                emit(
//                    Resource.Error(
//                        "Something went wrong ${response.code()}"
//                    )
//                )
//            }
//        } catch (e: HttpException) {
//            emit(
//                Resource.Error(
//                    "An expected error occurred!"
//                )
//            )
//        } catch (e: IOException) {
//            emit(
//                Resource.Error(
//                    "Couldn't reach server. Please check your internet connection!"
//                )
//            )
//        }
//    }

    override suspend fun getMinute(
        id: String, company: String
    ): Flow<Resource<List<GetMinuteModel>>> =
        loadResult({ mainApiService.getMinute(id, company) }, { data, flow ->

            minuteDao.deleteAllMinute(id, company)
            minuteDao.setMinute(data.map { it.toDbModel() })

            Log.d("impl____", "getMinute: ${
                minuteDao.getMinuteByIDList(id, company).map { it.toModel() }
            }")

            flow.emit(Resource.Success(data.map { it.toModel() }))
        }, { flow ->
            Log.d("impl____", "getMinute: ${
                minuteDao.getMinuteByIDList(id, company).map { it.toModel() }
            }")
            flow.emit(
                Resource.Success(minuteDao.getMinuteByIDList(id, company).map { it.toModel() })
            )
        })

//    override suspend fun getMinute(
//        id: String, company: String
//    ): Flow<Resource<List<GetMinuteModel>>> = flow {
//
//        emit(Resource.Success(minuteDao.getMinuteByIDList(id, company).map { it.toModel() }))
//
//
//        try {
//            val response = mainApiService.getMinute(id, company)
//
//            if (response.isSuccessful) {
//                if (response.body() != null) {
//                    val result = response.body()
//                    result?.data?.let { p ->
//
//                        minuteDao.deleteAllMinute()
//                        minuteDao.setMinute(p.map { it.toDbModel() })
//
//                        emit(
//                            Resource.Success(minuteDao.getMinuteByIDList(id, company)
//                                .map { it.toModel() })
//                        )
//                    }
//                } else {
//                    emit(
//                        Resource.Error(
//                            "An expected error occurred! Data is null"
//                        )
//                    )
//                }
//            } else {
//                emit(
//                    Resource.Error(
//                        "Something went wrong ${response.code()}"
//                    )
//                )
//            }
//        } catch (e: HttpException) {
//            emit(
//                Resource.Error(
//                    "An expected error occurred!"
//                )
//            )
//        } catch (e: IOException) {
//            emit(
//                Resource.Error(
//                    "Couldn't reach server. Please check your internet connection!"
//                )
//            )
//        }
//    }

    override suspend fun getNews(
        company: String
    ): Flow<Resource<List<GetNewsModel>>> = loadResult({
        mainApiService.getNews(company)
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.map { it.toModel() }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun getTariffType(company: String): Flow<Resource<List<GetTypeModel>>> =
        loadResult({ mainApiService.getTariffType(company) }, { data, flow ->

            tariffDao.deleteAllTariffType(company)
            tariffDao.setTariffType(data.map { it.toDbModel() })

            Log.d("impl____", "getTariffType: ${
                tariffDao.getTariffType(company).map { it.toModel() }
            }")

            flow.emit(Resource.Success(data.map { it.toModel() }))
        }, { flow ->
            Log.d("impl____", "getTariffType: ${
                tariffDao.getTariffType(company).map { it.toModel() }
            }")
            flow.emit(
                Resource.Success(tariffDao.getTariffType(company).map { it.toModel() })
            )
        })

//    override suspend fun getTariffType(company: String): Flow<Resource<List<GetTypeModel>>> = flow {
//
//        emit(Resource.Success(tariffDao.getTariffType(company).map { it.toModel() }))
//
//        try {
//            val response = mainApiService.getTariffType(company)
//
//            if (response.isSuccessful) {
//                if (response.body() != null) {
//                    val result = response.body()
//                    result?.data?.let { p ->
//
//                        tariffDao.deleteAllTariffType()
//                        tariffDao.setTariffType(p.map { it.toDbModel() })
//
//                        Log.d("loggggggg", "getInternetType: ${
//                            tariffDao.getTariffType(company).map { it.toModel() }
//                        }")
//
//                        emit(
//                            Resource.Success(tariffDao.getTariffType(company).map { it.toModel() })
//                        )
//                    }
//                } else {
//                    emit(
//                        Resource.Error(
//                            "An expected error occurred! Data is null"
//                        )
//                    )
//                }
//            } else {
//                emit(
//                    Resource.Error(
//                        "Something went wrong ${response.code()}"
//                    )
//                )
//            }
//        } catch (e: HttpException) {
//            emit(
//                Resource.Error(
//                    "An expected error occurred!"
//                )
//            )
//        } catch (e: IOException) {
//            emit(
//                Resource.Error(
//                    "Couldn't reach server. Please check your internet connection!"
//                )
//            )
//        }
//    }

    override suspend fun getTariff(
        company: String, id: String
    ): Flow<Resource<List<GetTariffModel>>> =
        loadResult({ mainApiService.getTariff(company, id) }, { data, flow ->

            tariffDao.deleteAllTariff(id, company)
            tariffDao.setTariff(data.map { it.toDbModel() })

            Log.d("impl____", "getTariff: ${
                tariffDao.getTariff(company, id).map { it.toModel() }
            }")

            flow.emit(Resource.Success(data.map { it.toModel() }))
        }, { flow ->
            Log.d("impl____", "getTariff: ${
                tariffDao.getTariff(company, id).map { it.toModel() }
            }")
            flow.emit(
                Resource.Success(tariffDao.getTariff(company, id).map { it.toModel() })
            )
        })


//    override suspend fun getTariff(
//        company: String, id: String
//    ): Flow<Resource<List<GetTariffModel>>> = flow {
//
//        emit(Resource.Success(tariffDao.getTariff(company, id).map { it.toModel() }))
//
//        try {
//            val response = mainApiService.getTariff(company, id)
//
//            if (response.isSuccessful) {
//                if (response.body() != null) {
//                    val result = response.body()
//                    result?.data?.let { p ->
//
//                        tariffDao.deleteAllTariff()
//                        tariffDao.setTariff(p.map { it.toDbModel() })
//
//                        Log.d("loggggggg", "getInternetType: ${
//                            internetDao.getInternetType(company).map { it.toModel() }
//                        }")
//
//                        emit(
//                            Resource.Success(tariffDao.getTariff(company, id).map { it.toModel() })
//                        )
//                    }
//                } else {
//                    emit(
//                        Resource.Error(
//                            "An expected error occurred! Data is null"
//                        )
//                    )
//                }
//            } else {
//                emit(
//                    Resource.Error(
//                        "Something went wrong ${response.code()}"
//                    )
//                )
//            }
//        } catch (e: HttpException) {
//            emit(
//                Resource.Error(
//                    "An expected error occurred!"
//                )
//            )
//        } catch (e: IOException) {
//            emit(
//                Resource.Error(
//                    "Couldn't reach server. Please check your internet connection!"
//                )
//            )
//        }
//    }

    override suspend fun getUssd(
        company: String
    ): Flow<Resource<List<GetUssdModel>>> = flow {

        emit(Resource.Success(ussdDao.getUssd(company).map { it.toModel() }))

        try {
            val response = mainApiService.getUssd(company)

            if (response.isSuccessful) {
                if (response.body() != null) {
                    val result = response.body()
                    result?.data?.let { p ->

                        ussdDao.setUssd(p.map { it.toDbModel() })

                        Log.d("loggggggg", "getInternetType: ${
                            internetDao.getInternetType(company).map { it.toModel() }
                        }")

                        emit(
                            Resource.Success(ussdDao.getUssd(company).map { it.toModel() })
                        )
                    }
                } else {
                    emit(
                        Resource.Error(
                            "An expected error occurred! Data is null"
                        )
                    )
                }
            } else {
                emit(
                    Resource.Error(
                        "Something went wrong ${response.code()}"
                    )
                )
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    "An expected error occurred!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    "Couldn't reach server. Please check your internet connection!"
                )
            )
        }
    }

}