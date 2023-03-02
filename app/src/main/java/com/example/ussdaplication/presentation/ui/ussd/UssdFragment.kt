package com.example.ussdaplication.presentation.ui.ussd

import androidx.lifecycle.lifecycleScope
import com.example.ussdaplication.App
import com.example.ussdaplication.BaseFragment
import com.example.ussdaplication.databinding.FragmentUssdBinding
import com.example.ussdaplication.presentation.ui.ussd.adapter.UssdAdapter
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class UssdFragment : BaseFragment<FragmentUssdBinding>(FragmentUssdBinding::inflate) {

    @Inject
    lateinit var viewModel: UssdViewModel

    private val adapter by lazy {
        UssdAdapter(requireContext())
    }

    override fun onViewCreate() {
        App.appComponent.inject(this)

        binding.list.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.getUssd(App.sharedPreference.operator.lowercase()).collectLatest {
                it.data?.let { p ->
                    adapter.setList(p)
                }
            }
        }
    }
}