package com.example.menutest.screens.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controller.DataController
import com.example.data.LoginRequest
import com.example.data.ResponseStatus
import com.example.menutest.constants.Constants
import com.example.menutest.helpers.PreferenceManager
import com.example.menutest.models.SignInStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val signInStatus: MutableLiveData<SignInStatus> = MutableLiveData(SignInStatus.IDLE)

    fun getSignInStatus(): MutableLiveData<SignInStatus> {
        return signInStatus
    }

    fun setSignInStatusIdle() {
        signInStatus.value = SignInStatus.IDLE
    }

    fun signIn(email: String, password: String) {
        signInStatus.value = SignInStatus.IN_PROGRESS

        viewModelScope.launch {
            // delay to fake loading
            delay(1000)

            // checking is email and password correct
            if (isEmailCorrect(email) && isPasswordCorrect(password)) {

                val response = DataController().login(LoginRequest(email, password))

                when (response.status) {

                    ResponseStatus.SUCCESS -> {
                        val statusSuccess = SignInStatus.SUCCESS
                        statusSuccess.msg = response.data
                        signInStatus.value = statusSuccess
                    }

                    ResponseStatus.ERROR -> {
                        val statusError = SignInStatus.FAILED
                        statusError.msg = response.error ?: "Some error occurred"
                        signInStatus.value = statusError
                    }

                    else -> {
                        val statusError = SignInStatus.FAILED
                        statusError.msg = "Some error occurred"
                        signInStatus.value = statusError
                    }

                }

            } else {
                val statusError = SignInStatus.FAILED
                statusError.msg = "You have entered an invalid username or password"
                signInStatus.value = SignInStatus.FAILED
            }

        }

    }

    // email is correct if contains '@' and '.' and if length is at least 5
    private fun isEmailCorrect(email: String): Boolean {
        return email.length > 4 && email.contains("@") && email.contains(".")
    }

    // minimal length of password should be at least 4
    private fun isPasswordCorrect(password: String): Boolean {
        return password.length > 3
    }

    fun saveToken(context: Context, token: String) {
        val preferenceManager = PreferenceManager(context)
        preferenceManager.setStringValue(Constants.PreferenceName.LOGIN_TOKEN, token)
    }

    fun isTokenSaved(context: Context): Boolean {
        val preferenceManager = PreferenceManager(context)
        return preferenceManager.containsKey(Constants.PreferenceName.LOGIN_TOKEN)
    }

}