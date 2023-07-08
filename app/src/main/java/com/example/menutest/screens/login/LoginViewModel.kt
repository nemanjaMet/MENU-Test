package com.example.menutest.screens.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.menutest.models.SignInStatus

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

        if (isEmailCorrect(email) && isPasswordCorrect(password)) {
            signInStatus.value = SignInStatus.SUCCESS
        } else {
            signInStatus.value = SignInStatus.FAILED
        }

    }

    private fun isEmailCorrect(email: String): Boolean {
        return email.length > 4 && email.contains("@") && email.contains(".")
    }

    private fun isPasswordCorrect(password: String): Boolean {
        return password.length > 3
    }

}