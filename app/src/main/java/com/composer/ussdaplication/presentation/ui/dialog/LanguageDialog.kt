package com.composer.ussdaplication.presentation.ui.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.composer.ussdaplication.App
import com.composer.ussdaplication.R
import com.composer.ussdaplication.databinding.BottomLanguageBinding
import com.composer.ussdaplication.utils.clearDrawables
import com.composer.ussdaplication.utils.setDrawable
import com.google.android.material.bottomsheet.BottomSheetDialog

class LanguageDialog(context: Context) :
    BottomSheetDialog(context, R.style.AppBottomSheetDialogTheme) {
    private val binding = BottomLanguageBinding.inflate(layoutInflater)

    private var itemClickListener: ((id: String) -> Unit)? = null

    fun setItemClickListener(f: (id: String) -> Unit) {
        itemClickListener = f
    }

    init {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        when (com.composer.ussdaplication.App.sharedPreference.lang) {
            "uz" -> uz()
            "ru" -> ru()
            "en" -> en()
        }

        binding.en.setOnClickListener {
            en()
            com.composer.ussdaplication.App.sharedPreference.lang = "en"
            itemClickListener?.invoke("en")
            dismiss()
        }
        binding.ru.setOnClickListener {
            ru()
            com.composer.ussdaplication.App.sharedPreference.lang = "ru"
            itemClickListener?.invoke("ru")
            dismiss()
        }
        binding.uz.setOnClickListener {
            uz()
            com.composer.ussdaplication.App.sharedPreference.lang = "uz"
            itemClickListener?.invoke("uz")
            dismiss()
        }

        setCancelable(true)
        setContentView(binding.root)
    }

    private fun en() {
        binding.uz.clearDrawables()
        binding.ru.clearDrawables()
        binding.en.setDrawable(R.drawable.ic_check)
    }

    private fun ru() {
        binding.en.clearDrawables()
        binding.uz.clearDrawables()
        binding.ru.setDrawable(R.drawable.ic_check)
    }

    private fun uz() {
        binding.ru.clearDrawables()
        binding.en.clearDrawables()
        binding.uz.setDrawable(R.drawable.ic_check)
    }
}