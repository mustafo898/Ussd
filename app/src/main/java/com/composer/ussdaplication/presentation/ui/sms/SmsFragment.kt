package com.composer.ussdaplication.presentation.ui.sms

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.composer.ussdaplication.App
import com.composer.ussdaplication.BaseFragment
import com.composer.ussdaplication.R
import com.composer.ussdaplication.databinding.FragmentSmsBinding
import com.composer.ussdaplication.domain.model.GetTypeModel
import com.composer.ussdaplication.presentation.ui.sms.adapter.SmsPagerAdapter
import com.composer.ussdaplication.utils.getColor
import com.composer.ussdaplication.utils.getCurrentLang
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class SmsFragment : BaseFragment<FragmentSmsBinding>(FragmentSmsBinding::inflate) {

    @Inject
    lateinit var viewModel: SmsViewModel

    override fun onViewCreate() {
        App.appComponent.inject(this)

        setTabColors()
        getType()
    }

    private fun setTabColors() {
        binding.tabLayout.setSelectedTabIndicatorColor(App.sharedPreference.operatorColor)
        binding.tabLayout.setTabTextColors(
            getColor(R.color.black),
            App.sharedPreference.operatorColor
        )
        (App.sharedPreference.operatorColor)
    }

    private fun getType() = lifecycleScope.launchWhenStarted {
        viewModel.getType(App.sharedPreference.operator.lowercase()).collectLatest {
            it.data?.let { p ->
                setViewPager(p)
            }
        }
    }

    private fun setViewPager(list: List<GetTypeModel>) {
        val idList = mutableListOf<String>()

        list.forEach {
            idList.add(it._id)
        }

        binding.viewPager.adapter = SmsPagerAdapter(childFragmentManager, lifecycle, idList)
//        binding.viewPager.isUserInputEnabled = false

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = getCurrentLang(list[position].name)
            tab.tag = list[position]._id
            Log.d("sslfdgkhdjlkgh", "setViewPager:")
        }.attach()
    }
}