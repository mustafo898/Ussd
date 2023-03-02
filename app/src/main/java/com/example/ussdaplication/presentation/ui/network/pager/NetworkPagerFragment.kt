package com.example.ussdaplication.presentation.ui.network.pager

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.example.ussdaplication.App
import com.example.ussdaplication.BaseFragment
import com.example.ussdaplication.databinding.FragmentNetworkPagerBinding
import com.example.ussdaplication.presentation.ui.dialog.ResponseDialog
import com.example.ussdaplication.presentation.ui.network.NetworkViewModel
import com.example.ussdaplication.presentation.ui.network.adapter.NetworkAdapter
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


private const val ARG_PARAM1 = "param1"

class NetworkPagerFragment :
    BaseFragment<FragmentNetworkPagerBinding>(FragmentNetworkPagerBinding::inflate) {

    private var param1: String = ""

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

    private val dialog by lazy {
        ResponseDialog(requireContext())
    }

    override fun onViewCreate() {
        App.appComponent.inject(this)

        binding.list.adapter = adapter

        arguments?.let {
            param1 = it.getString(ARG_PARAM1).toString()
        }

        getInternet(param1)

        adapter.setItemClickListener {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = ussdToCallableUri(it.code)

            try {
                startActivity(intent)
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
        }
    }

    private fun getInternet(id: String) = lifecycleScope.launchWhenStarted {
        viewModel.getInternet(id, App.sharedPreference.operator.lowercase()).collectLatest {
            it.data?.let { p ->
                adapter.setList(p)
            }
        }
    }
}