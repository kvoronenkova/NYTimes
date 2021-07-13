package com.ksu.exercise3.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.ksu.exercise3.R
import kotlinx.android.synthetic.main.fragment_detail_news.*

class DetailNewsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = requireArguments().getString(URL)

        webView.run{
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl(url!!)
        }

        val appBarLayout = view.findViewById<Toolbar>(R.id.toolbar)

        appBarLayout.apply {
            setNavigationOnClickListener{ requireActivity().onBackPressed()}
            title = requireNotNull(arguments).getString(CATEGORY)
        }
    }

    companion object {
        private const val CATEGORY = "CATEGORY"
        private const val URL = "URL"
    }
}