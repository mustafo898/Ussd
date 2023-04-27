package com.composer.ussdaplication.presentation.ui.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.composer.ussdaplication.R
import com.composer.ussdaplication.databinding.BottomResponseBinding
import com.composer.ussdaplication.utils.gone
import com.composer.ussdaplication.utils.visible
import com.google.android.material.bottomsheet.BottomSheetDialog

class ResponseDialog(context: Context) :
    BottomSheetDialog(context, R.style.AppBottomSheetDialogTheme) {
    private val binding = BottomResponseBinding.inflate(layoutInflater)

    fun setVisible(boolean: Boolean) {
        if (boolean) {
            setCancelable(false)
            binding.loader.visible()
            binding.text.gone()
        } else {
            setCancelable(true)
            binding.loader.gone()
            binding.text.visible()
        }
    }

    fun setText(text: String) {
        binding.text.text = text
    }

    init {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setCancelable(false)
        setContentView(binding.root)
    }
}