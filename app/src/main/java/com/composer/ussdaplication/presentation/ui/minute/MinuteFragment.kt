package com.composer.ussdaplication.presentation.ui.minute

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.composer.ussdaplication.App
import com.composer.ussdaplication.BaseFragment
import com.composer.ussdaplication.R
import com.composer.ussdaplication.databinding.FragmentMinuteBinding
import com.composer.ussdaplication.domain.model.GetTypeModel
import com.composer.ussdaplication.presentation.ui.minute.adapter.MinutePagerAdapter
import com.composer.ussdaplication.utils.getColor
import com.composer.ussdaplication.utils.getCurrentLang
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MinuteFragment : BaseFragment<FragmentMinuteBinding>(FragmentMinuteBinding::inflate) {

    @Inject
    lateinit var viewModel: MinuteViewModel

    override fun onViewCreate() {
        com.composer.ussdaplication.App.appComponent.inject(this)
        setTabColors()
        getType()
    }

    private fun getType() = lifecycleScope.launch {
        viewModel.getType(App.sharedPreference.operator.lowercase()).collectLatest {
            it.data?.let { p ->
                Log.d("sdklflhf", "getType: $p")
                setViewPager(p)
            }
        }
    }

    private fun setTabColors() {
        binding.tabLayout.setSelectedTabIndicatorColor(com.composer.ussdaplication.App.sharedPreference.operatorColor)
        binding.tabLayout.setTabTextColors(
            getColor(R.color.black),
            com.composer.ussdaplication.App.sharedPreference.operatorColor
        )
        (com.composer.ussdaplication.App.sharedPreference.operatorColor)
    }

    private fun setViewPager(list: List<GetTypeModel>) {
        val idList = mutableListOf<String>()

        list.forEach {
            idList.add(it._id)
            Log.d("sdklnsfjsbkf", "setViewPager: ${it._id}")
        }

        binding.viewPager.adapter = MinutePagerAdapter(childFragmentManager, lifecycle, idList)
//        binding.viewPager.isUserInputEnabled = false

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = getCurrentLang(list[position].name)
            tab.tag = list[position]._id
        }.attach()
    }
}