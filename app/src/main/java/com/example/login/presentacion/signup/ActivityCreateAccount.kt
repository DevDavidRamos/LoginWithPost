package com.example.login.presentacion.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.login.R
import com.example.login.databinding.ActivityCreateAccountBinding
import com.example.login.firebase.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityCreateAccount : Fragment() {
    private  var _binding: ActivityCreateAccountBinding? = null
    private val binding get() = _binding!!


    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = ActivityCreateAccountBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initListener()
    }


    private fun initObservers() {
        viewModel.sigUpState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is Resource.Succes -> {
                    handleLoading(isLoading = false)
                       activity?.onBackPressed()
                    Toast.makeText(
                        requireContext(),
                        "Sign up success",
                        Toast.LENGTH_SHORT
                    ).show()
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




    private fun initListener() {

        with(binding) {
            btnCrear.setOnClickListener {
                handleSignUp()
            }
            imageVolver.setOnClickListener {
                activity?.onBackPressed()
            }


        }

    }



        private fun handleSignUp() {

            val nombre = binding.txtNombre.text.toString()
            val apellido = binding.txtApellido.text.toString()
            val email = binding.txtEmail.text.toString()
            val password = binding.txtPassword.text.toString()

            viewModel.signUp(nombre,apellido,email, password)

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
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}