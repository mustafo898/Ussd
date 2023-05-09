package com.composer.ussdaplication.presentation.ui.network

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.composer.ussdaplication.App
import com.composer.ussdaplication.BaseFragment
import com.composer.ussdaplication.R
import com.composer.ussdaplication.databinding.FragmentNetworkBinding
import com.composer.ussdaplication.domain.model.GetTypeModel
import com.composer.ussdaplication.presentation.ui.network.adapter.NetworkPagerAdapter
import com.composer.ussdaplication.utils.getColor
import com.composer.ussdaplication.utils.getCurrentLang
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
        getType()
    }

    private fun getType() = lifecycleScope.launch {
//        viewModel.getType(App.sharedPreference.operator.lowercase()).collectLatest {
//            it.data?.let { p ->
//                Log.d("sdklflhf", "getType: $p")
//                setViewPager(p)
//            }
//        }
        Log.d("sdklflhf", "getType: $${App.sharedPreference.operator}")

        viewModel.getTypeDb(App.sharedPreference.operator.lowercase()).collectLatest {
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
    }

    private fun setViewPager(list: List<GetTypeModel>) {
        val idList = mutableListOf<String>()

        list.forEach {
            idList.add(it._id)
        }

        binding.viewPager.adapter = NetworkPagerAdapter(childFragmentManager, lifecycle, idList)
//        binding.viewPager.setPageTransformer(ZoomOutPageTransformer())

//        binding.viewPager.isUserInputEnabled = false

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = getCurrentLang(list[position].name)
            tab.tag = list[position]._id
        }.attach()
    }
}