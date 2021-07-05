package com.ksu.exercise3.presentation.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ksu.exercise3.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment() {
    private var userMessage: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email.setOnClickListener {
            userMessage = message.text.toString()
            emailSender(arrayOf("kvoronenkova@mail.com"), userMessage, Uri.parse("mailto:"))
        }
        telegram.setOnClickListener { openWebPage("https://web.telegram.org/#/login") }
        instagram.setOnClickListener { openWebPage("https://www.instagram.com/") }
        vk.setOnClickListener { openWebPage("https://vk.com/") }
    }

    private fun emailSender(addresses: Array<String>?, subject: String?, attachment: Uri?) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, addresses)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hello")
        intent.putExtra(Intent.EXTRA_TEXT, subject)
        intent.putExtra(Intent.EXTRA_STREAM, attachment)

        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun openWebPage(url: String?) {
        val webpage = Uri.parse(url)
        startActivity(Intent(Intent.ACTION_VIEW, webpage))
    }
}