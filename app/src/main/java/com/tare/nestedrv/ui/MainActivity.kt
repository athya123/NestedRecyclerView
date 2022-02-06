package com.tare.nestedrv.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.webkit.WebView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.tare.nestedrv.R
import com.tare.nestedrv.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            viewModel = homeViewModel
            lifecycleOwner = this@MainActivity
        }
        webView = binding.webView
    }

    override fun onBackPressed() {
        if (webView.visibility == View.VISIBLE) {
            webView.startAnimation(
                AnimationUtils.loadAnimation(
                    this,
                    android.R.anim.slide_out_right
                )
            )
            webView.visibility = View.INVISIBLE
        } else
            super.onBackPressed()

    }
}