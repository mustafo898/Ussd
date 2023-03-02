package com.example.ussdaplication.presentation.ui.minute

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.ussdaplication.App
import com.example.ussdaplication.BaseFragment
import com.example.ussdaplication.databinding.FragmentMinutePagerBinding
import com.example.ussdaplication.presentation.ui.dialog.ResponseDialog
import com.example.ussdaplication.presentation.ui.minute.adapter.MinuteAdapter
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
        ResponseDialog(requireContext())
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

    private fun getMinute(id: String) = lifecycleScope.launchWhenStarted {
        viewModel.getMinute(id, App.sharedPreference.operator.lowercase()).collectLatest {
            it.data?.let { p ->
                adapter.setList(p)
            }
        }
    }
}