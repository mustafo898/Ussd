package com.example.ussdaplication.presentation.ui.ussd

import com.example.ussdaplication.BaseFragment
import com.example.ussdaplication.databinding.FragmentUssdBinding
import com.example.ussdaplication.presentation.ui.ussd.adapter.UssdAdapter

class UssdFragment : BaseFragment<FragmentUssdBinding>(FragmentUssdBinding::inflate) {
    private val adapter by lazy {
        UssdAdapter(requireContext())
    }

    override fun onViewCreate() {
        binding.list.adapter = adapter
        val l = mutableListOf<String>()
        for (i in 0 until 15) {
            l.add("L")
        }
        adapter.setList(l)
    }
}