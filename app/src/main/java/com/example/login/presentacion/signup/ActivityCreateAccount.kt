package com.example.login.presentacion.signup

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewTreeLifecycleOwner
import com.example.login.R
import com.example.login.databinding.ActivityCreateAccountBinding
import com.example.login.firebase.util.Resource
import com.example.login.presentacion.login.MainActivity


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityCreateAccount : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageVolver.setOnClickListener { onBackPressed() }
        initListener()
        initObservers()


    }


    private fun initObservers() {
        viewModel.sigUpState.observe(this, Observer  { state ->
            when(state) {
                is Resource.Succes -> {
                    handleLoading(isLoading = false)
                    onBackPressed()
                    Toast.makeText(
                        this,
                        "Sign up success",
                        Toast.LENGTH_SHORT
                    ).show()
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




    private fun initListener() {

            binding.btnCrear.setOnClickListener {
                handleSignUp()
            }

}


        private fun handleSignUp() {


            val email = binding.txtEmail.text.toString()
            val password = binding.txtPassword.text.toString()

            viewModel.signUp(email, password)

        }
    private fun handleLoading(isLoading: Boolean){

        with(binding){
            if (isLoading){
                btnCrear.text = ""
                btnCrear.isEnabled = false
                pbSignUp.visibility = View.VISIBLE
            }else{
                pbSignUp.visibility = View.GONE
                btnCrear.text = getString(R.string.login__signup_button)
                btnCrear.isEnabled = true

            }
        }

    }


}