package com.mqweather.android.weather.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.mqweather.android.R
import kotlinx.android.synthetic.main.fragment_helpinfo.view.*

class HelpInfoFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_helpinfo, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.info)

        view.webview.settings.javaScriptEnabled = true
        view.webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let { view?.loadUrl(it) }
                return true
            }
        }
        view.webview.loadUrl("https://openweathermap.org/api")
        return view
    }
}