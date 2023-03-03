package com.example.ussdaplication.presentation.ui.settings

import android.content.Intent
import android.widget.Toast
import com.example.ussdaplication.App
import com.example.ussdaplication.BaseFragment
import com.example.ussdaplication.MainActivity
import com.example.ussdaplication.databinding.FragmentSettingsBinding
import com.example.ussdaplication.presentation.ui.dialog.LanguageDialog
import com.orhanobut.hawk.Hawk

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {
    private val dialog by lazy {
        LanguageDialog(requireContext())
    }

    override fun onViewCreate() {
        binding.language.setOnClickListener {
            dialog.show()
        }

        binding.languageTxt.text = App.sharedPreference.lang.uppercase()

        dialog.setItemClickListener {
            Hawk.put("pref_lang", it)
            requireActivity().startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}