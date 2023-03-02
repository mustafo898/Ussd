package com.example.ussdaplication.di.component

import com.example.ussdaplication.di.module.ApiServiceModule
import com.example.ussdaplication.di.module.DatabaseModule
import com.example.ussdaplication.di.module.NetworkModule
import com.example.ussdaplication.di.module.RepositoryModule
import com.example.ussdaplication.presentation.ui.definition.DefinitionFragment
import com.example.ussdaplication.presentation.ui.main.MainFragment
import com.example.ussdaplication.presentation.ui.minute.MinuteFragment
import com.example.ussdaplication.presentation.ui.minute.MinutePagerFragment
import com.example.ussdaplication.presentation.ui.network.NetworkFragment
import com.example.ussdaplication.presentation.ui.network.pager.NetworkPagerFragment
import com.example.ussdaplication.presentation.ui.sms.SmsFragment
import com.example.ussdaplication.presentation.ui.sms.pager.SmsPagerFragment
import com.example.ussdaplication.presentation.ui.ussd.UssdFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules =
    [
        ApiServiceModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        DatabaseModule::class,
    ]
)

interface AppComponent {
    fun inject(networkFragment: NetworkFragment)
    fun inject(networkFragment: MainFragment)
    fun inject(networkPagerFragment: NetworkPagerFragment)
    fun inject(smsFragment: SmsFragment)
    fun inject(smsPagerFragment: SmsPagerFragment)
    fun inject(minutePagerFragment: MinutePagerFragment)
    fun inject(minuteFragment: MinuteFragment)
    fun inject(ussdFragment: UssdFragment)
    fun inject(definitionFragment: DefinitionFragment)
}