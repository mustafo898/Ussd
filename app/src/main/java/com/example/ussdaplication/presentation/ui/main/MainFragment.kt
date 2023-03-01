package com.example.ussdaplication.presentation.ui.main

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.ussdaplication.App
import com.example.ussdaplication.BaseFragment
import com.example.ussdaplication.MainActivity
import com.example.ussdaplication.R
import com.example.ussdaplication.databinding.FragmentMainBinding
import com.example.ussdaplication.presentation.ui.main.adapter.ViewPagerAdapter
import com.example.ussdaplication.utils.*
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
        Log.d("slsddjflskhfd", "setNews: ")

        setUpViewPager()
        setUpToolBar()
        clickOperators()
        click()
    }

    private fun setUpToolBar() {
        when (App.sharedPreference.operator) {
            CompanyName.Ucell.name -> ucellColor()
            CompanyName.Beeline.name -> beelineColor()
            CompanyName.Mobiuz.name -> mobiColor()
            CompanyName.Uztelecom.name -> uztelecomColor()
            CompanyName.Humans.name -> humansColor()
        }
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
        }

        cardBeeline.setOnClickListener {
            beelineColor()
            (activity as MainActivity).setToolbarText(CompanyName.Beeline.name)
            setNews()
        }

        cardMobi.setOnClickListener {
            mobiColor()
            (activity as MainActivity).setToolbarText(CompanyName.Mobiuz.name)
            setNews()
        }

        cardUztelecom.setOnClickListener {
            uztelecomColor()
            (activity as MainActivity).setToolbarText(CompanyName.Uztelecom.name)
            setNews()
        }

        cardHumans.setOnClickListener {
            humansColor()
            (activity as MainActivity).setToolbarText(CompanyName.Humans.name)
            setNews()
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
            navController.navigate(R.id.action_mainFragment_to_definitionFragment)
        }
    }
}