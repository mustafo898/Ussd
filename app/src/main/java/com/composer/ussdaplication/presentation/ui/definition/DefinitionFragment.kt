package com.composer.ussdaplication.presentation.ui.definition

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.composer.ussdaplication.App
import com.composer.ussdaplication.BaseFragment
import com.composer.ussdaplication.databinding.FragmentDefinitionBinding
import com.composer.ussdaplication.presentation.ui.definition.adapter.DefinitionAdapter
import com.composer.ussdaplication.presentation.ui.dialog.ConfirmDialog
import com.composer.ussdaplication.utils.activateCode
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"

class DefinitionFragment :
    BaseFragment<FragmentDefinitionBinding>(FragmentDefinitionBinding::inflate) {

    private var param1: String = ""

    @Inject
    lateinit var viewModel: DefinitionViewModel

    private val adapter by lazy {
        DefinitionAdapter(requireContext())
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) = DefinitionFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
            }
        }
    }

    private val dialog by lazy {
        ConfirmDialog(requireActivity())
    }

    override fun onViewCreate() {
        App.appComponent.inject(this)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1).toString()
        }

        binding.list.adapter = adapter

        getList()

        var code = ""

        adapter.setItemClickListener {
            code = it.code
            dialog.show()
        }

        dialog.setActivateClickListener {
            activateCode(requireContext(), code)
            dialog.dismiss()
        }
    }

    private fun getList() = lifecycleScope.launchWhenStarted {
        viewModel.getTariff(App.sharedPreference.operator.lowercase(), param1).collectLatest {
            it.data?.let { p ->
                adapter.setList(p)
            }
        }
    }
}