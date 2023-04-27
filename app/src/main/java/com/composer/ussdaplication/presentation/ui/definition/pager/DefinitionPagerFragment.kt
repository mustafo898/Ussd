package com.composer.ussdaplication.presentation.ui.definition.pager

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.composer.ussdaplication.App
import com.composer.ussdaplication.BaseFragment
import com.composer.ussdaplication.R
import com.composer.ussdaplication.databinding.FragmentDefinitionPagerBinding
import com.composer.ussdaplication.domain.model.GetTypeModel
import com.composer.ussdaplication.presentation.ui.definition.DefinitionViewModel
import com.composer.ussdaplication.presentation.ui.definition.adapter.DefinitionPagerAdapter
import com.composer.ussdaplication.presentation.ui.network.adapter.NetworkPagerAdapter
import com.composer.ussdaplication.utils.getColor
import com.composer.ussdaplication.utils.getCurrentLang
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class DefinitionPagerFragment :
    BaseFragment<FragmentDefinitionPagerBinding>(FragmentDefinitionPagerBinding::inflate) {

    @Inject
    lateinit var viewModel: DefinitionViewModel

    override fun onViewCreate() {
        App.appComponent.inject(this)
        getType()
        setTabColors()
    }

    private fun getType() = lifecycleScope.launch {
        viewModel.getTariffType(App.sharedPreference.operator.lowercase()).collectLatest {
            it.data?.let { p ->
                Log.d("sdklflhf", "getType: $p")
                setViewPager(p)
                p.forEach { m ->
                    lifecycleScope.launch(Dispatchers.IO) {
                        viewModel.getTariff(m._id, App.sharedPreference.operator.lowercase())
                    }
                }
            }
        }
    }

    private fun setTabColors() {
        binding.tabLayout.setSelectedTabIndicatorColor(App.sharedPreference.operatorColor)
        binding.tabLayout.setTabTextColors(
            getColor(R.color.black), App.sharedPreference.operatorColor
        )
    }

    private fun setViewPager(list: List<GetTypeModel>) {
        val idList = mutableListOf<String>()

        list.forEach {
            idList.add(it._id)
        }

        binding.viewPager.adapter = DefinitionPagerAdapter(childFragmentManager, lifecycle, idList)
        binding.viewPager.isUserInputEnabled = false

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = getCurrentLang(list[position].name)
            tab.tag = list[position]._id
        }.attach()
    }

}