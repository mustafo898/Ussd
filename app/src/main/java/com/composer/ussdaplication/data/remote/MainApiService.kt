package com.composer.ussdaplication.data.remote

import com.composer.ussdaplication.data.remote.dto.MainResponseDto
import com.composer.ussdaplication.data.remote.dto.ads.GetNewsDto
import com.composer.ussdaplication.data.remote.dto.internet.GetInternetDto
import com.composer.ussdaplication.data.remote.dto.internet.GetInternetTypeDto
import com.composer.ussdaplication.data.remote.dto.minute.GetMinuteDto
import com.composer.ussdaplication.data.remote.dto.minute.GetMinuteTypeDto
import com.composer.ussdaplication.data.remote.dto.sms.GetSmsDto
import com.composer.ussdaplication.data.remote.dto.sms.GetSmsTypeDto
import com.composer.ussdaplication.data.remote.dto.tarif.GetTariffDto
import com.composer.ussdaplication.data.remote.dto.tarif.GetTariffTypeDto
import com.composer.ussdaplication.data.remote.dto.ussd.GetUssdDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApiService {

    @GET("internet/type/all")
    suspend fun getInternetType(
        @Query("company") company: String,
    ): Response<MainResponseDto<List<GetInternetTypeDto>>>

    @GET("tariff/type/all")
    suspend fun getTariffType(
        @Query("company") company: String,
    ): Response<MainResponseDto<List<GetTariffTypeDto>>>

    @GET("internet/all/{id}")
    suspend fun getInternet(
        @Path("id") id: String,
        @Query("company") company: String,
    ): Response<MainResponseDto<List<GetInternetDto>>>

    @GET("minute/type/all")
    suspend fun getMinuteType(
        @Query("company") company: String,
    ): Response<MainResponseDto<List<GetMinuteTypeDto>>>

    @GET("minute/all/{id}")
    suspend fun getMinute(
        @Path("id") id: String,
        @Query("company") company: String,
    ): Response<MainResponseDto<List<GetMinuteDto>>>

    @GET("sms/type/all")
    suspend fun getSmsType(
        @Query("company") company: String,
    ): Response<MainResponseDto<List<GetSmsTypeDto>>>

    @GET("sms/all/{id}")
    suspend fun getSms(
        @Path("id") id: String,
        @Query("company") company: String,
    ): Response<MainResponseDto<List<GetSmsDto>>>

    @GET("tariffs/all/{id}")
    suspend fun getTariff(
        @Path("id") id: String,
        @Query("company") company: String,
    ): Response<MainResponseDto<List<GetTariffDto>>>

    @GET("ussd/all")
    suspend fun getUssd(
        @Query("company") company: String,
    ): Response<MainResponseDto<List<GetUssdDto>>>

    @GET("news/all")
    suspend fun getNews(
        @Query("company") company: String,
    ): Response<MainResponseDto<List<GetNewsDto>>>
}