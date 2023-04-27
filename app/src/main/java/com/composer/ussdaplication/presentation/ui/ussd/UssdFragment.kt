package com.composer.ussdaplication.presentation.ui.ussd

import androidx.lifecycle.lifecycleScope
import com.composer.ussdaplication.App
import com.composer.ussdaplication.BaseFragment
import com.composer.ussdaplication.databinding.FragmentUssdBinding
import com.composer.ussdaplication.presentation.ui.dialog.ConfirmDialog
import com.composer.ussdaplication.presentation.ui.ussd.adapter.UssdAdapter
import com.composer.ussdaplication.utils.activateCode
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class UssdFragment : BaseFragment<FragmentUssdBinding>(FragmentUssdBinding::inflate) {

    @Inject
    lateinit var viewModel: UssdViewModel

    private val adapter by lazy {
        UssdAdapter(requireContext())
    }

    private val dialog by lazy {
        ConfirmDialog(requireActivity())
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
}