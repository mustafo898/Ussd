package com.example.ussdaplication

import android.app.Application
import android.content.res.Resources
import android.telephony.TelephonyManager
import com.droidnet.DroidNet
import com.example.ussdaplication.di.component.AppComponent
import com.example.ussdaplication.di.component.DaggerAppComponent
import com.example.ussdaplication.utils.SharedPreference
import com.example.ussdaplication.di.module.ApiServiceModule
import com.example.ussdaplication.di.module.NetworkModule
import com.example.ussdaplication.di.module.RepositoryModule

class App : Application() {

    companion object {
        lateinit var resources: Resources
        lateinit var appComponent: AppComponent
        const val LANGUAGE_UZBEK = "uz"
        const val LANGUAGE_UZBEK_COUNTRY = "UZ"
        const val LANGUAGE_RUSSIAN = "ru"
        const val LANGUAGE_RUSSIAN_COUNTRY = "RU"
        const val LANGUAGE_ENGLISH = "en"
        const val LANGUAGE_ENGLISH_COUNTRY = "EN"
        lateinit var sharedPreference: SharedPreference
        lateinit var mDroidNet: DroidNet
        var IS_DUAL: Boolean = false
        var BALANCE = ""
    }

    override fun onCreate() {
        super.onCreate()

        Companion.resources = resources

        appComponent = DaggerAppComponent.builder()
            .repositoryModule(RepositoryModule())
            .apiServiceModule(ApiServiceModule())
            .networkModule(NetworkModule(this))
            .build()

        sharedPreference = SharedPreference.getInstance(this)

        val telephonyManager = this.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        val networkOperatorName = telephonyManager.networkOperatorName
        sharedPreference.operator = networkOperatorName
    }
}