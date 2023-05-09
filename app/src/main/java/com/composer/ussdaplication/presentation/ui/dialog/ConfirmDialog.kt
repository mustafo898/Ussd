package com.composer.ussdaplication.presentation.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.composer.ussdaplication.App
import com.composer.ussdaplication.R
import com.composer.ussdaplication.databinding.DialogConfirmBinding

class ConfirmDialog(
    context: Context,
) : AlertDialog(context) {

    var binding: DialogConfirmBinding = DialogConfirmBinding.inflate(layoutInflater)

    private var activateClickListener: (() -> Unit)? = null

    fun setActivateClickListener(f: () -> Unit) {
        activateClickListener = f
    }

    fun setTitle(enable: Boolean, isSee: Int = 0) {
        if (enable) {
            if (isSee == 0) {
                binding.title.text = context.getString(R.string.want_activate)
                binding.activateTxt.text = context.getString(R.string.activate)
            }
            if (isSee == 1) {
                binding.title.text = context.getString(R.string.change_tariff)
                binding.activateTxt.text = context.getString(R.string.yes)
            }
        } else {
            binding.title.text = context.getString(R.string.want_deactivate)
            binding.activateTxt.text = context.getString(R.string.delete)
        }
    }

    init {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

        setCancelable(true)

        binding.linear.setBackgroundColor(App.sharedPreference.operatorColor)

        binding.disActivate.setOnClickListener {
            dismiss()
        }

        binding.activate.setOnClickListener {
            activateClickListener?.invoke()
        }

        setView(binding.root)
    }
}