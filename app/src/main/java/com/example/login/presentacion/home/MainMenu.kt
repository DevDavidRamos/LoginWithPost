package com.example.login.presentacion.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login.databinding.ActivityMainMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenu : AppCompatActivity() {
    private  lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}