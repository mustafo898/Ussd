package com.example.ussdaplication.presentation.ui.network

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.ussdaplication.App
import com.example.ussdaplication.BaseFragment
import com.example.ussdaplication.R
import com.example.ussdaplication.databinding.FragmentNetworkBinding
import com.example.ussdaplication.domain.model.GetTypeModel
import com.example.ussdaplication.presentation.ui.network.adapter.NetworkPagerAdapter
import com.example.ussdaplication.utils.getColor
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class NetworkFragment : BaseFragment<FragmentNetworkBinding>(FragmentNetworkBinding::inflate) {
    @Inject
    lateinit var viewModel: NetworkViewModel

    override fun onViewCreate() {
        App.appComponent.inject(this)

        setTabColors()
        clickTab()
        getType()
    }

    private fun getInternet(id: String) = lifecycleScope.launch {
        viewModel.getInternet(id, App.sharedPreference.operator.lowercase()).collectLatest {
            it.data?.let { p ->
                Log.d("sdklflhf", "getInternet: $p")
            }
        }
    }

    private fun getType() = lifecycleScope.launch {
        viewModel.getType().collectLatest {
            it.data?.let { p ->
                Log.d("sdklflhf", "getType: $p")
                setViewPager(p)
            }
        }
    }

    private fun setTabColors() {
        binding.tabLayout.setSelectedTabIndicatorColor(App.sharedPreference.operatorColor)
        binding.tabLayout.setTabTextColors(
            getColor(R.color.black), App.sharedPreference.operatorColor
        )
        (App.sharedPreference.operatorColor)
    }

    private fun setViewPager(list: List<GetTypeModel>) {
        val idList = mutableListOf<String>()

        list.forEach {
            idList.add(it._id)
        }

        binding.viewPager.adapter = NetworkPagerAdapter(childFragmentManager, lifecycle, idList)
        binding.viewPager.isUserInputEnabled = false

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = list[position].name
            tab.tag = list[position]._id
        }.attach()
    }

    private fun clickTab() = binding.apply {
        tabLayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { viewPager.currentItem = it.position }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }
}