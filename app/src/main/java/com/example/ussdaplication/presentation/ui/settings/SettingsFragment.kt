package com.example.ussdaplication.presentation.ui.settings

import android.widget.Toast
import com.example.ussdaplication.BaseFragment
import com.example.ussdaplication.databinding.FragmentSettingsBinding
import com.example.ussdaplication.presentation.ui.dialog.LanguageDialog

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {
    private val dialog by lazy {
        LanguageDialog(requireContext())
    }

    override fun onViewCreate() {
        binding.language.setOnClickListener {
            dialog.show()
        }

        dialog.setItemClickListener {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}