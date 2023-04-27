package com.composer.ussdaplication.presentation.ui.main

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.composer.ussdaplication.App
import com.composer.ussdaplication.BaseFragment
import com.composer.ussdaplication.MainActivity
import com.composer.ussdaplication.R
import com.composer.ussdaplication.databinding.FragmentMainBinding
import com.composer.ussdaplication.presentation.ui.main.adapter.ViewPagerAdapter
import com.composer.ussdaplication.utils.*
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    @Inject
    lateinit var viewModel: MainViewModel

    private val adapterViewPager by lazy {
        ViewPagerAdapter(requireContext())
    }

    override fun onViewCreate() {
        App.appComponent.inject(this)

        setUpViewPager()
        setUpToolBar()
        clickOperators()
        click()
        App.sharedPreference.lang = App.sharedPreference.secondLang
    }

    private fun setUpToolBar() {
        if (App.sharedPreference.operator.contains(CompanyName.Ucell.name))
            ucellColor()

        if (App.sharedPreference.operator.contains(CompanyName.Uztelecom.name))
            uztelecomColor()

        if (App.sharedPreference.operator.contains(CompanyName.Mobiuz.name))
            mobiColor()

        if (App.sharedPreference.operator.contains(CompanyName.Beeline.name))
            beelineColor()

        if (App.sharedPreference.operator.contains(CompanyName.Humans.name))
            humansColor()
    }

    private fun setUpViewPager() = binding.apply {
        viewPager.adapter = adapterViewPager
        springDotsIndicator.attachTo(viewPager)
        viewPager.autoScroll(4000)
        setNews()
    }

    private fun setNews() = lifecycleScope.launchWhenStarted {
        viewModel.getNews(App.sharedPreference.operator.lowercase()).collectLatest {
            it.data?.let { p ->
                Log.d("slsddjflskhfd", "setNews: $p")
                adapterViewPager.setList(p)
            }
        }
    }

//    private fun runService(){
//        lifecycleScope.launch(Dispatchers.IO) {
//            getListForRoom("ru")
//        }
//        lifecycleScope.launch(Dispatchers.IO) {
//            getListForRoom("uz")
//        }
//        lifecycleScope.launch(Dispatchers.IO) {
//            getListForRoom("en")
//        }
//    }

//    private fun getListForRoom(lang: String) {
//        getMinuteType(lang)
//        getSmsType(lang)
//        getInternetType(lang)
//        getUssd(lang)
//        getTariff(lang)
//    }

//    private fun getInternetType(lang: String) = lifecycleScope.launchWhenStarted {
//        launch(Dispatchers.IO) {
//            viewModel.getTypeI(
//                App.sharedPreference.operator.lowercase(),
//                lang
//            ).collectLatest {
//                it.data?.let { p ->
//                    if (p.isNotEmpty())
//                        p.forEach { i ->
//                            lifecycleScope.launch(Dispatchers.IO) {
//                                viewModel.getInternet(
//                                    i._id,
//                                    App.sharedPreference.operator.lowercase(),
//                                    lang
//                                )
//                            }
//                        }
//                }
//            }
//        }
//    }
//
//    private fun getSmsType(lang: String) = lifecycleScope.launchWhenStarted {
//        launch {
//            viewModel.getTypeS(
//                App.sharedPreference.operator.lowercase(),
//                lang
//            ).collectLatest {
//                it.data?.let { p ->
//                    if (p.isNotEmpty())
//                        p.forEach { o ->
//                            lifecycleScope.launch(Dispatchers.IO) {
//                                viewModel.getSms(
//                                    o._id,
//                                    App.sharedPreference.operator.lowercase(),
//                                    lang
//                                )
//                            }
//                        }
//                }
//            }
//        }
//    }
//
//    private fun getMinuteType(lang: String) = lifecycleScope.launchWhenStarted {
//        launch(Dispatchers.IO) {
//            viewModel.getTypeM(
//                App.sharedPreference.operator.lowercase(),
//                lang
//            ).collectLatest {
//                it.data?.let { p ->
//                    if (p.isNotEmpty())
//                        p.forEach { o ->
//                            lifecycleScope.launch(Dispatchers.IO) {
//                                viewModel.getMinute(
//                                    o._id,
//                                    App.sharedPreference.operator.lowercase(),
//                                    lang
//                                )
//                            }
//                        }
//                }
//            }
//        }
//    }
//
//    private fun getUssd(lang: String) = lifecycleScope.launchWhenStarted {
//        launch(Dispatchers.IO) {
//            viewModel.getUssd(
//                App.sharedPreference.operator.lowercase(),
//                lang
//            ).collectLatest {
//                it.data?.let { p ->
//                    Log.d("sdjfskdjfdlfhks", ": getUssd : $p")
//                }
//            }
//        }
//    }
//
//    private fun getTariff(lang: String) = lifecycleScope.launchWhenStarted {
//        launch(Dispatchers.IO) {
//            viewModel.getTariff(
//                App.sharedPreference.operator.lowercase(),
//                lang
//            ).collectLatest {
//                it.data?.let { p ->
//                    Log.d("sdjfskdjfdlfhks", ": getUssd : $p")
//                }
//            }
//        }
//    }

    private fun setDefault() = binding.apply {
        animateCard(internet)
        animateCard(sms)
        animateCard(tarif)
        animateCard(minute)
        animateCard(ussd)
        animateCard(service)

        binding.springDotsIndicator.setStrokeDotsIndicatorColor(getColor(R.color.ucell))

        imageUcell.setImage(R.drawable.ucell)
        cardUcell.setBackColor(R.color.white)

        imageBeeline.setImage(R.drawable.beeline_black)
        cardBeeline.setBackColor(R.color.white)

        imageMobi.setImage(R.drawable.mobiuz_red)
        cardMobi.setBackColor(R.color.white)

        imageUztelecom.setImage(R.drawable.uztelecom_blue)
        cardUztelecom.setBackColor(R.color.white)

        imageHumans.setImage(R.drawable.humans)
        cardHumans.setBackColor(R.color.white)
    }

    private fun clickOperators() = binding.apply {
        cardUcell.setOnClickListener {
            ucellColor()
            (activity as MainActivity).setToolbarText(CompanyName.Ucell.name)
            setNews()
//            runService()
        }

        cardBeeline.setOnClickListener {
            beelineColor()
            (activity as MainActivity).setToolbarText(CompanyName.Beeline.name)
            setNews()
//            runService()
        }

        cardMobi.setOnClickListener {
            mobiColor()
            (activity as MainActivity).setToolbarText(CompanyName.Mobiuz.name)
            setNews()
//            runService()
        }

        cardUztelecom.setOnClickListener {
            uztelecomColor()
            (activity as MainActivity).setToolbarText(CompanyName.Uztelecom.name)
            setNews()
//            runService()
        }

        cardHumans.setOnClickListener {
            humansColor()
            (activity as MainActivity).setToolbarText(CompanyName.Humans.name)
            setNews()
//            runService()
        }
    }

    private fun ucellColor() = binding.apply {
        setDefault()
        binding.springDotsIndicator.setStrokeDotsIndicatorColor(getColor(R.color.ucell))
        imageUcell.setImage(R.drawable.ucell_white)
        cardUcell.setBackColor(R.color.ucell)
        (activity as MainActivity).setBottomColor(R.color.ucell)
    }

    private fun beelineColor() = binding.apply {
        setDefault()
        binding.springDotsIndicator.setStrokeDotsIndicatorColor(getColor(R.color.beeline))
        imageBeeline.setImage(R.drawable.beeline_black)
        cardBeeline.setBackColor(R.color.beeline)
        (activity as MainActivity).setBottomColor(R.color.beeline)
    }

    private fun mobiColor() = binding.apply {
        setDefault()
        binding.springDotsIndicator.setStrokeDotsIndicatorColor(getColor(R.color.mobi))
        imageMobi.setImage(R.drawable.mobiuz)
        cardMobi.setBackColor(R.color.mobi)
        (activity as MainActivity).setBottomColor(R.color.mobi)
    }

    private fun uztelecomColor() = binding.apply {
        setDefault()
        binding.springDotsIndicator.setStrokeDotsIndicatorColor(getColor(R.color.uztelecom))
        imageUztelecom.setImage(R.drawable.uztelecom)
        cardUztelecom.setBackColor(R.color.uztelecom)
        (activity as MainActivity).setBottomColor(R.color.uztelecom)
    }

    private fun humansColor() = binding.apply {
        setDefault()
        binding.springDotsIndicator.setStrokeDotsIndicatorColor(getColor(R.color.humans))
        imageHumans.setImage(R.drawable.humans)
        cardHumans.setBackColor(R.color.humans)
        (activity as MainActivity).setBottomColor(R.color.humans)
    }

    private fun click() {
        binding.minute.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_minuteFragment)
        }
        binding.internet.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_networkFragment)
        }
        binding.sms.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_smsFragment)
        }
        binding.ussd.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_ussdFragment)
        }
        binding.tarif.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_definitionPagerFragment)
        }
    }
}