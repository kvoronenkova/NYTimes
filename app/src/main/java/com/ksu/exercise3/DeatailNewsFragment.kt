package com.ksu.exercise3

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.ksu.exercise3.dto.NewsDTO
import kotlinx.android.synthetic.main.fragment_detail_news.*
import kotlinx.android.synthetic.main.fragment_detail_news.view.*

class DetailNewsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView.settings.javaScriptEnabled = true
        val url = arguments?.getString(URL)
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url!!)
        val appBarLayout = view.findViewById<Toolbar>(R.id.toolbar)
        appBarLayout.setNavigationOnClickListener { v: View? -> requireActivity().onBackPressed() }
        appBarLayout.title = arguments?.getString(CATEGORY)
    }

    companion object {
        private const val CATEGORY = "CATEGORY"
        private const val URL = "URL"
    }
}