package com.composer.ussdaplication.presentation.ui.minute

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.composer.ussdaplication.App
import com.composer.ussdaplication.BaseFragment
import com.composer.ussdaplication.databinding.FragmentMinutePagerBinding
import com.composer.ussdaplication.presentation.ui.dialog.ConfirmDialog
import com.composer.ussdaplication.presentation.ui.minute.adapter.MinuteAdapter
import com.composer.ussdaplication.utils.activateCode
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"

class MinutePagerFragment :
    BaseFragment<FragmentMinutePagerBinding>(FragmentMinutePagerBinding::inflate) {

    private var param1: String = ""

    private val adapter by lazy {
        MinuteAdapter(requireContext())
    }

    @Inject
    lateinit var viewModel: MinuteViewModel

    private val dialog by lazy {
        ConfirmDialog(requireActivity())
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) = MinutePagerFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
            }
        }
    }

    override fun onViewCreate() {
        App.appComponent.inject(this)

        arguments?.let {
            param1 =
                it.getString(ARG_PARAM1)
                    .toString()
        }

        binding.list.adapter = adapter

        getMinute(param1)

        var code = ""

        adapter.setItemClickListener {
            code = it.turnOn
        }

        dialog.setActivateClickListener {
            activateCode(requireContext(), code)
            dialog.dismiss()
        }
    }

    private fun getMinute(id: String) = lifecycleScope.launchWhenStarted {
        viewModel.getMinute(
            id,
            App.sharedPreference.operator.lowercase(),
        ).collectLatest {
            it.data?.let { p ->
                adapter.setList(p)
            }
        }
    }
}