package com.composer.ussdaplication.presentation.ui.sms.pager

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.composer.ussdaplication.App
import com.composer.ussdaplication.BaseFragment
import com.composer.ussdaplication.databinding.FragmentSmsPagerBinding
import com.composer.ussdaplication.presentation.ui.dialog.ConfirmDialog
import com.composer.ussdaplication.presentation.ui.sms.SmsViewModel
import com.composer.ussdaplication.presentation.ui.sms.adapter.SmsAdapter
import com.composer.ussdaplication.utils.activateCode
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

    private val dialog by lazy {
        ConfirmDialog(requireActivity())
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) = SmsPagerFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
            }
        }
    }

    override fun onViewCreate() {
        App.appComponent.inject(this)

        binding.list.adapter = adapter

        arguments?.let {
            param1 = it.getString(ARG_PARAM1).toString()
        }

        var code = ""

        adapter.setItemClickListener {
            code = it.turnOn
        }

        dialog.setActivateClickListener {
            activateCode(requireContext(), code)
            dialog.dismiss()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.getSms(
                param1,
                App.sharedPreference.operator.lowercase(),
            ).collectLatest {
                it.data?.let { p ->
                    adapter.setList(p)
                }
            }
        }
    }
}