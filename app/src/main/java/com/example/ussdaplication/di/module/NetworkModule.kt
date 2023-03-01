package com.example.ussdaplication.di.module

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.ussdaplication.App
import com.example.ussdaplication.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule(val context: Context) {

    @Singleton
    @Provides
    fun provideGsonConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()

//    @Singleton
//    @Provides
//    fun provideEncryptedSharedPreference(): SharedPreference = SharedPreference.getInstance(context)

    @Singleton
    @Provides
    fun provideOkHttpClient(
//        encryptedSharedPref: SharedPreference
    ): OkHttpClient {
        val chuckInterceptor = ChuckerInterceptor.Builder(context).maxContentLength(500_000L)
            .alwaysReadResponseBody(true).build()
        val builder = OkHttpClient.Builder().addInterceptor(chuckInterceptor)
            .connectTimeout(10000L, TimeUnit.SECONDS)
            .addNetworkInterceptor(Interceptor { chain: Interceptor.Chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${App.sharedPreference.token}")
                    .addHeader("lang", App.sharedPreference.lang.lowercase())
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json").build()
                chain.proceed(request)
            }).build()
        return builder
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonGsonConverterFactory: GsonConverterFactory, builder: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(builder).addConverterFactory(gsonGsonConverterFactory).build()
    }
}