package com.example.menutest.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.menutest.R
import com.example.menutest.databinding.FragmentLoginBinding
import com.example.menutest.models.SignInStatus
import com.example.menutest.screens.MasterScreenFragment

class LoginScreenFragment : MasterScreenFragment() {

    companion object {
        private const val LOG_TAG = "LoginScreenFragment"
    }

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListener()
        setViewModelObservers()
    }

    private fun setOnClickListener() {

        binding?.apply {

            btnSignIn.setOnClickListener {
                it.setOnClickListener(null)

                val email = etEmail.text.toString()
                val password = etPassword.text.toString()

                viewModel.signIn(email, password)
            }

        }

    }

    private fun setViewModelObservers() {

        viewModel.getSignInStatus().observe(viewLifecycleOwner) { status ->

            when (status) {

                SignInStatus.IN_PROGRESS -> {

                }

                SignInStatus.FAILED -> {
                    viewModel.setSignInStatusIdle()
                    setOnClickListener()
                    showSnackbar("Error")
                }

                SignInStatus.SUCCESS -> {
                    viewModel.setSignInStatusIdle()
                    clearInputFields()
                    openVenuesListScreen()
                }

                else -> {

                }

            }

        }

    }

    private fun openVenuesListScreen() {
        findNavController().navigate(R.id.action_loginScreenFragment_to_venuesListScreenFragment)
    }

    private fun clearInputFields() {
        binding?.apply {
            etEmail.setText("")
            etPassword.setText("")
        }
    }

}