package com.example.login.presentacion.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.login.R
import com.example.login.databinding.ActivityMainBinding
import com.example.login.firebase.util.Resource
import com.example.login.presentacion.home.MainMenu
import com.example.login.presentacion.signup.ActivityCreateAccount
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistro.setOnClickListener { initListener() }
        binding.btnLogin.setOnClickListener { handleLogin() }
        initObservers()

    }
    private fun initObservers() {
        viewModel.loginState.observe(this, Observer  { state ->
            when(state) {
                is Resource.Succes -> {
                    handleLoading(isLoading = false)
                   initListener2()


                }
                is Resource.Error -> {
                    handleLoading(isLoading = false)
                    Toast.makeText(
                        this,
                        state.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Loading -> handleLoading(isLoading = true)
                else -> Unit
            }
        })
    }

    private fun initListener2(){
        val intent = Intent(this,MainMenu::class.java)

        startActivity(intent)

    }
private fun initListener(){
    val intent = Intent(this,ActivityCreateAccount::class.java)

    startActivity(intent)


}

    private fun handleLogin() {


        val email = binding.txtEmail.text.toString()
        val password = binding.txtPassword.text.toString()

        viewModel.login(email, password)




    }
    private fun handleLoading(isLoading: Boolean){

        with(binding){
            if (isLoading){
                btnLogin.text = ""
                btnLogin.isEnabled = false
                pbLogin.visibility = View.VISIBLE
            }else{
                pbLogin.visibility = View.GONE
                btnLogin.text = getString(R.string.login_button)
                btnLogin.isEnabled = true

            }
        }

    }



}