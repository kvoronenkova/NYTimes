package com.ksu.exercise3.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ksu.exercise3.R
import com.ksu.exercise3.presentation.main.MainFragment

class MainActivity : AppCompatActivity() {
    private val newsRecyclerFragment = MainFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(newsRecyclerFragment, "Frg1")
        fragmentTransaction.commit()
    }

}