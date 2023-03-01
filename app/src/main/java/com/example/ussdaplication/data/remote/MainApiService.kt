package com.example.ussdaplication.data.remote

import com.example.ussdaplication.data.remote.dto.GetTypeDto
import com.example.ussdaplication.data.remote.dto.MainResponseDto
import com.example.ussdaplication.data.remote.dto.ads.GetNewsDto
import com.example.ussdaplication.data.remote.dto.internet.GetInternetDto
import com.example.ussdaplication.data.remote.dto.minute.GetMinuteDto
import com.example.ussdaplication.data.remote.dto.sms.GetSmsDto
import com.example.ussdaplication.data.remote.dto.tarif.GetTariffDto
import com.example.ussdaplication.data.remote.dto.ussd.GetUssdDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApiService {

    @GET("internet/type/all")
    suspend fun getInternetType(): Response<MainResponseDto<List<GetTypeDto>>>

    @GET("internet/all/{id}")
    suspend fun getInternet(
        @Path("id") id: String,
        @Query("company") company: String
    ): Response<MainResponseDto<List<GetInternetDto>>>

    @GET("minute/type/all")
    suspend fun getMinuteType(): Response<MainResponseDto<List<GetTypeDto>>>

    @GET("minute/all/{id}")
    suspend fun getMinute(
        @Path("id") id: String,
        @Query("company") company: String
    ): Response<MainResponseDto<List<GetMinuteDto>>>


    @GET("sms/type/all")
    suspend fun getSmsType(): Response<MainResponseDto<List<GetTypeDto>>>

    @GET("sms/all/{id}")
    suspend fun getSms(
        @Path("id") id: String,
        @Query("company") company: String
    ): Response<MainResponseDto<List<GetSmsDto>>>

    @GET("tariffs/all")
    suspend fun getTariff(
        @Query("company") company: String
    ): Response<MainResponseDto<List<GetTariffDto>>>

    @GET("ussd/all")
    suspend fun getUssd(
        @Query("company") company: String
    ): Response<MainResponseDto<List<GetUssdDto>>>

    @GET("news/all")
    suspend fun getNews(
        @Query("company") company: String
    ): Response<MainResponseDto<List<GetNewsDto>>>
}