package com.example.ussdaplication.presentation.ui.minute

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.ussdaplication.App
import com.example.ussdaplication.BaseFragment
import com.example.ussdaplication.R
import com.example.ussdaplication.databinding.FragmentMinuteBinding
import com.example.ussdaplication.domain.model.GetTypeModel
import com.example.ussdaplication.presentation.ui.minute.adapter.MinutePagerAdapter
import com.example.ussdaplication.utils.getColor
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MinuteFragment : BaseFragment<FragmentMinuteBinding>(FragmentMinuteBinding::inflate) {

    @Inject
    lateinit var viewModel: MinuteViewModel

    override fun onViewCreate() {
        App.appComponent.inject(this)
        setTabColors()
        getType()
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
            getColor(R.color.black),
            App.sharedPreference.operatorColor
        )
        (App.sharedPreference.operatorColor)
    }

    private fun setViewPager(list: List<GetTypeModel>) {
        val idList = mutableListOf<String>()

        list.forEach {
            idList.add(it._id)
            Log.d("sdklnsfjsbkf", "setViewPager: ${it._id}")
        }

        binding.viewPager.adapter = MinutePagerAdapter(childFragmentManager, lifecycle, idList)
        binding.viewPager.isUserInputEnabled = false

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = list[position].name
            tab.tag = list[position]._id
        }.attach()
    }
}