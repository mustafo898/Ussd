package com.composer.ussdaplication

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.composer.ussdaplication.databinding.ActivityMainBinding
import com.composer.ussdaplication.utils.*
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var controller: NavController

    private val listFragments = listOf(R.id.mainFragment, R.id.settings)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val supportFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        controller = supportFragment.findNavController()

        controller.enableOnBackPressed(true)

        dexter()
        setupSmoothBottomMenu()

        App.sharedPreference.secondLang = App.sharedPreference.lang

        binding.toolbar.text.text = App.sharedPreference.operator

        controller.addOnDestinationChangedListener { _, destination, arguments ->
            binding.toolbarBack.nameBar.text = destination.label
            animateCard(binding.toolbarBack.nameBar)

            if (listFragments[1] == destination.id) {
                binding.toolbar.text.text = destination.label
            }
            if (listFragments[0] == destination.id) {
                binding.toolbar.text.text = com.composer.ussdaplication.App.sharedPreference.operator
            }

            if (listFragments.contains(destination.id)) {
                showToolBar()
            } else {
                hideToolBar()
            }
        }

        binding.toolbarBack.back.setOnClickListener {
            controller.popBackStack()
        }
    }

    private fun setupSmoothBottomMenu() {
        val popupMenu = PopupMenu(this, null)
        popupMenu.inflate(R.menu.bottom_nav)
        val menu = popupMenu.menu
        binding.bottomNav.setupWithNavController(menu, controller)
    }

    fun setBottomColor(data: Int) {
        binding.bottomNav.barIndicatorColor = getColor(data)
        com.composer.ussdaplication.App.sharedPreference.operatorColor = getColor(data)
    }

    fun setToolbarText(data: String) = binding.apply {
        toolbar.text.text = data
        com.composer.ussdaplication.App.sharedPreference.operator = data
    }

    private fun dexter() {
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_PHONE_NUMBERS,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.RECEIVE_SMS
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    try {
                        com.composer.ussdaplication.App.IS_DUAL = getOperatorsName(this@MainActivity).size == 2
                        com.composer.ussdaplication.App.sharedPreference.operator = getOperatorsName(this@MainActivity)[0]
                        Log.d(
                            "asdlkfjfkhsdjfd",
                            "onPermissionsChecked: ${com.composer.ussdaplication.App.sharedPreference.operator}"
                        )
                    } catch (e: Exception) {
                        log(e.toString())
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken?
                ) {
                    dexter()
                }
            }).check()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showToast(this, getString(R.string.permission_no))
            } else {
                showToast(this, getString(R.string.permission_yes))
            }
        }
    }

    private fun showToolBar() {
        binding.toolbar.toolbar.visible()
        binding.bottom.visible()
        binding.toolbarBack.toolbar.gone()
    }

    private fun hideToolBar() {
        binding.toolbar.toolbar.gone()
        binding.bottom.gone()
        binding.toolbarBack.toolbar.visible()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase?.let { ControlLanguage.setLocale(it) })
    }
}