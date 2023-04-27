package com.composer.ussdaplication

import android.app.Application
import android.content.res.Resources
import android.telephony.TelephonyManager
import com.composer.ussdaplication.di.component.AppComponent
import com.composer.ussdaplication.di.component.DaggerAppComponent
import com.composer.ussdaplication.di.module.ApiServiceModule
import com.composer.ussdaplication.di.module.DatabaseModule
import com.composer.ussdaplication.di.module.NetworkModule
import com.composer.ussdaplication.di.module.RepositoryModule
import com.composer.ussdaplication.utils.SharedPreference
import com.droidnet.DroidNet
import com.orhanobut.hawk.Hawk

class App : Application() {

    companion object {
        lateinit var reSources: Resources
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

        reSources = resources

        Hawk.init(this).build()

        appComponent = DaggerAppComponent.builder().repositoryModule(RepositoryModule())
            .apiServiceModule(ApiServiceModule()).databaseModule(DatabaseModule(this))
            .networkModule(NetworkModule(this)).build()

        sharedPreference = SharedPreference.getInstance(this)

        val telephonyManager = this.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        val networkOperatorName = telephonyManager.networkOperatorName
        sharedPreference.operator = networkOperatorName

        Hawk.put(
            "pref_lang", sharedPreference.lang.lowercase()
        )
    }
}