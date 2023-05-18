package com.example.login.presentacion.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.login.R
import androidx.navigation.fragment.findNavController
import com.example.login.databinding.ActivityMainBinding
import com.example.login.firebase.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :Fragment(){

    private  var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = ActivityMainBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initListener()
    }

    private fun initObservers() {
        viewModel.loginState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is Resource.Succes -> {
                    handleLoading(isLoading = false)
                    val action = MainActivityDirections.actionMainActivityToMainMenu(uid = state.data.uid)
                    findNavController().navigate(action)


                }
                is Resource.Error -> {
                    handleLoading(isLoading = false)
                    Toast.makeText(
                        requireContext(),
                        state.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Loading -> handleLoading(isLoading = true)
                else -> Unit
            }
        }
    }


private fun initListener(){
    with(binding) {
        btnLogin.setOnClickListener { handleLogin() }
        btnRegistro.setOnClickListener { findNavController().navigate(R.id.action_mainActivity_to_activityCreateAccount) }
        PasswordRecovery.setOnClickListener { findNavController().navigate(R.id.action_mainActivity_to_passwordRecovery) }
    }


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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}