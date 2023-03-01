package com.example.ussdaplication.presentation.ui.minute

import com.example.ussdaplication.App
import com.example.ussdaplication.BaseFragment
import com.example.ussdaplication.R
import com.example.ussdaplication.databinding.FragmentMinuteBinding
import com.example.ussdaplication.presentation.ui.minute.adapter.MinutePagerAdapter
import com.example.ussdaplication.utils.getColor
import com.google.android.material.tabs.TabLayoutMediator

class MinuteFragment : BaseFragment<FragmentMinuteBinding>(FragmentMinuteBinding::inflate) {
    override fun onViewCreate() {
        setViewPager()
        setTabColors()
    }

    private fun setTabColors() {
        binding.tabLayout.setSelectedTabIndicatorColor(App.sharedPreference.operatorColor)
        binding.tabLayout.setTabTextColors(
            getColor(R.color.black),
            App.sharedPreference.operatorColor
        )
        (App.sharedPreference.operatorColor)
    }

    private fun setViewPager() {
        val list = mutableListOf("Пакеты минут", "Fresh - Пакеты")
        binding.viewPager.adapter = MinutePagerAdapter(childFragmentManager, lifecycle, list)
        binding.viewPager.isUserInputEnabled = false

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = list[position]
        }.attach()
    }
}