package com.composer.ussdaplication.presentation.ui.network.pager

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.composer.ussdaplication.App
import com.composer.ussdaplication.BaseFragment
import com.composer.ussdaplication.databinding.FragmentNetworkPagerBinding
import com.composer.ussdaplication.presentation.ui.dialog.ConfirmDialog
import com.composer.ussdaplication.presentation.ui.network.NetworkViewModel
import com.composer.ussdaplication.presentation.ui.network.adapter.NetworkAdapter
import com.composer.ussdaplication.utils.activateCode
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


private const val ARG_PARAM1 = "param1"

class NetworkPagerFragment :
    BaseFragment<FragmentNetworkPagerBinding>(FragmentNetworkPagerBinding::inflate) {

    private var param1: String = ""

    private val dialog by lazy {
        ConfirmDialog(requireActivity())
    }

    @Inject
    lateinit var viewModel: NetworkViewModel

    companion object {
        @JvmStatic
        fun newInstance(param1: String) = NetworkPagerFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
            }
        }
    }

    private val adapter by lazy {
        NetworkAdapter(requireContext())
    }

    override fun onViewCreate() {
        App.appComponent.inject(this)

        binding.list.adapter = adapter

        arguments?.let {
            param1 = it.getString(ARG_PARAM1).toString()
        }

        getInternet(param1)

        var code = ""

        adapter.setItemClickListener {
            code = it.turnOn
            dialog.show()
        }

        dialog.setActivateClickListener {
            activateCode(requireContext(), code)
            dialog.dismiss()
        }
    }

    private fun getInternet(id: String) = lifecycleScope.launchWhenStarted {
        viewModel.getInternet(
            id, App.sharedPreference.operator.lowercase()
        ).collectLatest {
            it.data?.let { p ->
                Log.d("dsfsdfsdf", "getInternet: $p")
                adapter.setList(p)
            }
        }
    }
}