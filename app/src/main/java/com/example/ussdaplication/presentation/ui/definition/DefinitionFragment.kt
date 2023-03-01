package com.example.ussdaplication.presentation.ui.definition

import androidx.lifecycle.lifecycleScope
import com.example.ussdaplication.App
import com.example.ussdaplication.BaseFragment
import com.example.ussdaplication.databinding.FragmentDefinitionBinding
import com.example.ussdaplication.presentation.ui.definition.adapter.DefinitionAdapter
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class DefinitionFragment :
    BaseFragment<FragmentDefinitionBinding>(FragmentDefinitionBinding::inflate) {

    @Inject
    lateinit var viewModel: DefinitionViewModel

    private val adapter by lazy {
        DefinitionAdapter(requireContext())
    }

    override fun onViewCreate() {
        binding.list.adapter = adapter

        getList()
    }

    private fun getList() = lifecycleScope.launchWhenStarted {
        viewModel.getTariff(App.sharedPreference.operator.lowercase()).collectLatest {
            it.data?.let { p ->
                adapter.setList(p)
            }
        }
    }
}