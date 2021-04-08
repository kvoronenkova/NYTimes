package com.ksu.exercise3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

class IntroActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        startSecondActivity()
    }

    private fun startSecondActivity() {
        startActivity(Intent(this, NewsRecyclerActivity::class.java))
        finish()
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }
}