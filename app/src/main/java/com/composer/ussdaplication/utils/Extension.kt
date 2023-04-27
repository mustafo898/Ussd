package com.composer.ussdaplication.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Context.TELEPHONY_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.telephony.TelephonyManager.UssdResponseCallback
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.composer.ussdaplication.App
import com.composer.ussdaplication.App.Companion.reSources
import com.composer.ussdaplication.R
import com.composer.ussdaplication.domain.model.LanguageModel
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo


fun ViewPager2.autoScroll(interval: Long) {

    val handler = Handler()
    var scrollPosition = 0

    val runnable = object : Runnable {

        override fun run() {

            /**
             * Calculate "scroll position" with
             * adapter pages count and current
             * value of scrollPosition.
             */
            val count = adapter?.itemCount ?: 0
            if (count > 0)
                setCurrentItem(scrollPosition++ % count, true)

            handler.postDelayed(this, interval)
        }
    }

    object : OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            // Updating "scroll position" when user scrolls manually
            scrollPosition = position + 1
        }

        override fun onPageScrollStateChanged(state: Int) {
            // Not necessary
        }

        override fun onPageScrolled(
            position: Int, positionOffset: Float, positionOffsetPixels: Int
        ) {
            // Not necessary
        }
    }

    handler.post(runnable)
}

fun showToast(context: Context, str: String) {
    Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun getColor(color: Int): Int {
    return reSources.getColor(color)
}

fun ImageView.setImage(image: Int) {
    this.setImageResource(image)
}

fun ImageView.loadImage(image: String) {
    Glide.with(context).load(image).into(this)
}

fun View.setBackColor(color: Int) {
    this.setBackgroundColor(resources.getColor(color))
}

@SuppressLint("NewApi")
fun callUssd(activity: Activity, code: String) {
//    var ussd = code.substring(0, code.length - 1)
//
//    ussd += Uri.parse("#")

    if (ActivityCompat.checkSelfPermission(
            activity,
            Manifest.permission.CALL_PHONE
        ) != PackageManager.PERMISSION_GRANTED
    ) {
//        val ussdResponseCallback =


        return
    }
}

fun activate(context: Context, code: String) {
    try {
        if (!App.IS_DUAL) {
            if (getOperatorsName(context)[0].contains(App.sharedPreference.operator)) {
                val manager = context.getSystemService(TELEPHONY_SERVICE) as TelephonyManager

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (ActivityCompat.checkSelfPermission(
                            context,
                            Manifest.permission.CALL_PHONE
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        return
                    }

                    manager.sendUssdRequest(code, object : UssdResponseCallback() {
                        override fun onReceiveUssdResponse(
                            telephonyManager: TelephonyManager,
                            request: String,
                            response: CharSequence
                        ) {
                            //if our request is successful then we get response here
                            Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
                        }

                        override fun onReceiveUssdResponseFailed(
                            telephonyManager: TelephonyManager,
                            request: String,
                            failureCode: Int
                        ) {

                            //request failures will be catched here
                            Toast.makeText(context, failureCode, Toast.LENGTH_SHORT).show()

                            //here failure reasons are in the form of failure codes
                        }
                    }, Handler())
                    Toast.makeText(context, "Activated", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(
                    context,
                    context.getString(
                        R.string.no_simcard,
                        com.composer.ussdaplication.App.sharedPreference.operator
                    ),
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            if (getOperatorsName(context)[0].contains(com.composer.ussdaplication.App.sharedPreference.operator) || getOperatorsName(
                    context
                )[1].contains(com.composer.ussdaplication.App.sharedPreference.operator)
            ) {
                Toast.makeText(context, "Activated", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    context,
                    context.getString(
                        R.string.no_simcard,
                        com.composer.ussdaplication.App.sharedPreference.operator
                    ),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun ussdToCallableUri(ussd: String, context: Context) {
    var uriString: String? = ""
    if (!ussd.startsWith("tel:")) uriString += "tel:"
    for (c in ussd.toCharArray()) {
        if (c == '#') uriString += Uri.encode("#") else uriString += c
    }
    val intent = Intent(Intent.ACTION_CALL)
    intent.data = Uri.parse(uriString)

    try {
        startActivity(context, intent, null)
    } catch (e: SecurityException) {
        e.printStackTrace()
    }
//            return Uri.parse(uriString)
}

fun activateCode(context: Context, code: String) {
    if (code.isNotEmpty())
        if (getOperatorsName(context)[0].contains(App.sharedPreference.operator))
            ussdToCallableUri(code, context)
        else
            Toast.makeText(
                context,
                context.getString(R.string.no_simcard, App.sharedPreference.operator),
                Toast.LENGTH_SHORT
            ).show()
}

fun Any.log(str: String) {
    Log.d(this.javaClass.name, str)
}

fun getOperatorsName(context: Context): ArrayList<String> {
    val data = ArrayList<String>()
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_PHONE_STATE
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            (context as Activity),
            arrayOf(Manifest.permission.READ_PHONE_STATE),
            1
        )
    }
    val subscriptionInfoData = SubscriptionManager.from(context).activeSubscriptionInfoList
    if (subscriptionInfoData != null && subscriptionInfoData.size > 0) {
        for (j in subscriptionInfoData.indices) {
            data.add(subscriptionInfoData[j].carrierName.toString())
            Log.d(
                "SJSJJDJDJD",
                "getOperatorsName: ${subscriptionInfoData[j].carrierName}"
            )
        }
    }
    return data
}

fun TextView.setDrawable(@DrawableRes image: Int) {
    this.setCompoundDrawablesWithIntrinsicBounds(0, 0, image, 0)
}

fun TextView.clearDrawables() {
    this.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
}

fun animateCard(view: View) {
    YoYo.with(Techniques.Shake)
        .duration(700)
        .repeat(0)
        .playOn(view)
}

fun getString(@StringRes int: Int) = reSources.getString(int)

fun getString(@StringRes int: Int, vararg arg: Any) = reSources.getString(int)

fun getCurrentLang(data: LanguageModel): String = when (App.sharedPreference.lang.lowercase()) {
    "en" -> data.en
    "ru" -> data.ru
    "uz" -> data.uz
    else -> data.en
}