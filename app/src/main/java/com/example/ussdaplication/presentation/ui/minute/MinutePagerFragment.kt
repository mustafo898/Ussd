package com.example.ussdaplication.presentation.ui.minute

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Handler
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.ussdaplication.BaseFragment
import com.example.ussdaplication.databinding.FragmentMinutePagerBinding
import com.example.ussdaplication.presentation.ui.dialog.ResponseDialog
import com.example.ussdaplication.presentation.ui.minute.adapter.MinuteAdapter

class MinutePagerFragment :
    BaseFragment<FragmentMinutePagerBinding>(FragmentMinutePagerBinding::inflate) {
    private val adapter by lazy {
        MinuteAdapter(requireContext())
    }

    private val dialog by lazy {
        ResponseDialog(requireContext())
    }

    override fun onViewCreate() {
        binding.list.adapter = adapter
        val l = mutableListOf<String>()
        for (i in 0 until 15) {
            l.add("L")
        }

        adapter.setList(l)

        adapter.setItemClickListener {
//            active("*100#")

        }
    }

    private fun active(code: String) {
        val manager =
            requireActivity().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager


        dialog.setVisible(true)
        dialog.show()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }

            manager.sendUssdRequest(code, object : TelephonyManager.UssdResponseCallback() {
                override fun onReceiveUssdResponse(
                    telephonyManager: TelephonyManager,
                    request: String,
                    response: CharSequence
                ) {
                    dialog.setText(response.toString())
                    dialog.setVisible(false)
                }

                override fun onReceiveUssdResponseFailed(
                    telephonyManager: TelephonyManager,
                    request: String,
                    failureCode: Int
                ) {

                    dialog.dismiss()
                    Toast.makeText(context, "$failureCode", Toast.LENGTH_SHORT).show()
                }
            }, Handler())
        }
    }
}