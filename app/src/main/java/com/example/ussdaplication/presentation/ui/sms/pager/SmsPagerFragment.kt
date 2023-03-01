package com.example.ussdaplication.presentation.ui.sms.pager

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.ussdaplication.App
import com.example.ussdaplication.BaseFragment
import com.example.ussdaplication.databinding.FragmentSmsPagerBinding
import com.example.ussdaplication.presentation.ui.network.pager.NetworkPagerFragment
import com.example.ussdaplication.presentation.ui.sms.SmsViewModel
import com.example.ussdaplication.presentation.ui.sms.adapter.SmsAdapter
import com.example.ussdaplication.presentation.ui.sms.adapter.SmsPagerAdapter
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"

class SmsPagerFragment : BaseFragment<FragmentSmsPagerBinding>(FragmentSmsPagerBinding::inflate) {
    private var param1: String = ""

    @Inject
    lateinit var viewModel: SmsViewModel

    private val adapter by lazy {
        SmsAdapter(requireContext())
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            SmsPagerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun onViewCreate() {
        App.appComponent.inject(this)

        Log.d("dkspfdjj", "onViewCreate: ")

        binding.list.adapter = adapter

        arguments?.let {
            param1 =
                it.getString(ARG_PARAM1)
                    .toString()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.getSms(param1, App.sharedPreference.operator.lowercase()).collectLatest {
                it.data?.let { p ->
                    adapter.setList(p)
                }
            }
        }
    }
}