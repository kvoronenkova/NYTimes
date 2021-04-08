package com.ksu.exercise3

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.ksu.exercise3.dto.NewsDTO
import kotlinx.android.synthetic.main.activity_full_news.*

class FullNewsActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_news)

        webView.getSettings().javaScriptEnabled = true
        val url = intent.getStringExtra(URL)
        webView.setWebViewClient(WebViewClient())
        webView.loadUrl(url!!)
        val appBarLayout = findViewById<Toolbar>(R.id.toolbar)
        appBarLayout.setNavigationOnClickListener { v: View? -> onBackPressed() }
        appBarLayout.title = intent.getStringExtra(CATEGORY)
    }

    companion object {
        private const val CATEGORY = "CATEGORY"
        private const val URL = "URL"
        fun start(activity: Activity, newsItem: NewsDTO) {
            val intent = Intent(activity, FullNewsActivity::class.java)
            intent.putExtra(URL, newsItem.url)
            activity.startActivity(intent)
        }
    }
}