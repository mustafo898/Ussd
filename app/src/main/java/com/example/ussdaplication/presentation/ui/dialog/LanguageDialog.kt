package com.example.ussdaplication.presentation.ui.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.ussdaplication.App
import com.example.ussdaplication.R
import com.example.ussdaplication.databinding.BottomLanguageBinding
import com.example.ussdaplication.utils.clearDrawables
import com.example.ussdaplication.utils.setDrawable
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

        when (App.sharedPreference.lang) {
            "Uz" -> uz()
            "Ru" -> ru()
            "En" -> en()
        }

        binding.en.setOnClickListener {
            en()
            App.sharedPreference.lang = "en"
            itemClickListener?.invoke(binding.en.text.toString())
            dismiss()
        }
        binding.ru.setOnClickListener {
            ru()
            App.sharedPreference.lang = "ru"
            itemClickListener?.invoke(binding.ru.text.toString())
            dismiss()
        }
        binding.uz.setOnClickListener {
            uz()
            App.sharedPreference.lang = "uz"
            itemClickListener?.invoke(binding.uz.text.toString())
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