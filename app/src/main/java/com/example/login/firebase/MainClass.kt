package com.example.login.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainClass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_class)
    }
}