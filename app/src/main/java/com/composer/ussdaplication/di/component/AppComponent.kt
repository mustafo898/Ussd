package com.composer.ussdaplication.di.component

import com.composer.ussdaplication.di.module.ApiServiceModule
import com.composer.ussdaplication.di.module.DatabaseModule
import com.composer.ussdaplication.di.module.NetworkModule
import com.composer.ussdaplication.di.module.RepositoryModule
import com.composer.ussdaplication.presentation.ui.definition.DefinitionFragment
import com.composer.ussdaplication.presentation.ui.definition.pager.DefinitionPagerFragment
import com.composer.ussdaplication.presentation.ui.main.MainFragment
import com.composer.ussdaplication.presentation.ui.minute.MinuteFragment
import com.composer.ussdaplication.presentation.ui.minute.MinutePagerFragment
import com.composer.ussdaplication.presentation.ui.network.NetworkFragment
import com.composer.ussdaplication.presentation.ui.network.pager.NetworkPagerFragment
import com.composer.ussdaplication.presentation.ui.sms.SmsFragment
import com.composer.ussdaplication.presentation.ui.sms.pager.SmsPagerFragment
import com.composer.ussdaplication.presentation.ui.ussd.UssdFragment
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
    fun inject(definitionPagerFragment: DefinitionPagerFragment)
}