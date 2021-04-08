package com.ksu.exercise3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class AboutActivity : AppCompatActivity() {
    private var userMessage: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email.setOnClickListener {
            userMessage = message.getText().toString()
            emailSender(arrayOf("kvoronenkova@mail.com"), userMessage, Uri.parse("mailto:"))
        }
        telegram.setOnClickListener { openWebPage("https://web.telegram.org/#/login") }
        instagram.setOnClickListener { openWebPage("https://www.instagram.com/") }
        vk.setOnClickListener { openWebPage("https://vk.com/") }
    }

    fun emailSender(addresses: Array<String>?, subject: String?, attachment: Uri?) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, addresses)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hello")
        intent.putExtra(Intent.EXTRA_TEXT, subject)
        intent.putExtra(Intent.EXTRA_STREAM, attachment)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun openWebPage(url: String?) {
        val webpage = Uri.parse(url)
        startActivity(Intent(Intent.ACTION_VIEW, webpage))
    }
}