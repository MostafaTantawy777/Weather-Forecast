package com.tantawy.weatherforecast.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.tantawy.weatherforecast.R
import com.tantawy.weatherforecast.base.activity.BaseActivity
import com.tantawy.weatherforecast.presentation.viewmodel.WeatherViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val productsViewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            productsViewModel.apply {
                weather.observe(this@MainActivity) {
                    it?.let {
                        Log.e("MainActivity", it[0]?.main?.humidity.toString())
                    }
                }
            }
        }
    }
}