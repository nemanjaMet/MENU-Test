package com.example.menutest.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.menutest.R
import com.example.menutest.databinding.FragmentLoginBinding
import com.example.menutest.models.SignInStatus
import com.example.menutest.screens.MasterScreenFragment
import com.example.menutest.shared_view_models.SharedViewModel

class LoginScreenFragment : MasterScreenFragment() {

    companion object {
        private const val LOG_TAG = "LoginScreenFragment"
    }

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding
    private val viewModel: LoginViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.checkIsTokenSaved(requireContext())
    }

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

        viewModel.signInStatus.observe(viewLifecycleOwner) { status ->

            when (status) {

                SignInStatus.IN_PROGRESS -> {
                    showProgressBar()
                }

                SignInStatus.FAILED -> {
                    val errorMsg = viewModel.getErrorMessage(requireContext(), status.msg, status.errorCode)
                    viewModel.setSignInStatusIdle()
                    setOnClickListener()
                    showSnackbarError(errorMsg)
                }

                SignInStatus.SUCCESS -> {
                    viewModel.saveToken(requireContext(), status.msg)
                    viewModel.setSignInStatusIdle()
                    clearInputFields()
                    openVenuesListScreen()
                }

                else -> {
                    hideProgressBar()
                }

            }

        }

        viewModel.isTokenSavedState.observe(viewLifecycleOwner) { isTokenSaved ->

            isTokenSaved?.let { isSaved ->

                if (isSaved) {
                    openVenuesListScreen()
                }

                sharedViewModel.hideSplashScreen()
                viewModel.isTokenSavedState.value = null
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

    override fun onBackPressed() {
        activity?.finish()
    }

}