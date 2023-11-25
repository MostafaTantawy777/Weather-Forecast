package com.tantawy.weatherforecast.base

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import com.tantawy.weatherforecast.databinding.LoadingBinding

class CustomProgressDialog(private val activity: Activity) : Dialog(activity) {

    private lateinit var loadingBinding: LoadingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        loadingBinding = LoadingBinding.inflate(layoutInflater)
        setContentView(loadingBinding.root)
        setCancelable(true)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        loadingBinding.apply {
            loadingView.background = ColorDrawable(Color.TRANSPARENT)
            loadingView.visibility = View.VISIBLE
        }
    }
}