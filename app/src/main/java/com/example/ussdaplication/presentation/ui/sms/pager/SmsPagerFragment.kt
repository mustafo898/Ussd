package com.example.ussdaplication.presentation.ui.sms.pager

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.ussdaplication.App
import com.example.ussdaplication.BaseFragment
import com.example.ussdaplication.databinding.FragmentSmsPagerBinding
import com.example.ussdaplication.presentation.ui.sms.SmsViewModel
import com.example.ussdaplication.presentation.ui.sms.adapter.SmsAdapter
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

        binding.list.adapter = adapter

        arguments?.let {
            param1 =
                it.getString(ARG_PARAM1)
                    .toString()
        }

        adapter.setItemClickListener {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = ussdToCallableUri(it.code)

            try {
                startActivity(intent)
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
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