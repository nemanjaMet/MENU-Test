package com.example.menutest.screens.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controller.DataController
import com.example.data.LoginRequest
import com.example.data.ResponseStatus
import com.example.menutest.R
import com.example.menutest.constants.Constants
import com.example.menutest.helpers.PreferenceManager
import com.example.menutest.models.SignInStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _signInStatus: MutableLiveData<SignInStatus> = MutableLiveData(SignInStatus.IDLE)
    val signInStatus get() = _signInStatus
    private val _isTokenSavedState: MutableLiveData<Boolean> = MutableLiveData()
    val isTokenSavedState get() = _isTokenSavedState


    fun setSignInStatusIdle() {
        _signInStatus.value = SignInStatus.IDLE
    }

    fun signIn(email: String, password: String) {
        _signInStatus.value = SignInStatus.IN_PROGRESS

        viewModelScope.launch {
            // delay to fake loading ;; it was left on purpose
            delay(500)

            // checking is email and password correct
            if (isEmailCorrect(email) && isPasswordCorrect(password)) {

                val response = DataController().login(LoginRequest(email, password))

                when (response.status) {

                    ResponseStatus.SUCCESS -> {
                        val statusSuccess = SignInStatus.SUCCESS
                        statusSuccess.msg = response.data
                        _signInStatus.value = statusSuccess
                    }

                    ResponseStatus.ERROR -> {
                        val statusError = SignInStatus.FAILED
                        statusError.msg = response.error ?: ""
                        _signInStatus.value = statusError
                    }

                    ResponseStatus.CONNECTION_ERROR -> {
                        val statusError = SignInStatus.FAILED
                        statusError.errorCode = Constants.ErrorCode.NO_INTERNET_CONNECTION
                        statusError.msg = response.error ?: ""
                        _signInStatus.value = statusError
                    }

                    else -> {
                        val statusError = SignInStatus.FAILED
                        _signInStatus.value = statusError
                    }

                }

            } else {
                val statusError = SignInStatus.FAILED
                statusError.errorCode = Constants.ErrorCode.INVALID_CREDENTIALS
                _signInStatus.value = SignInStatus.FAILED
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
        preferenceManager.setStringValue(Constants.PreferenceName.ACCESS_TOKEN, token)
    }

    fun checkIsTokenSaved(context: Context) {
        val preferenceManager = PreferenceManager(context)
        val isTokenSaved = preferenceManager.containsKey(Constants.PreferenceName.ACCESS_TOKEN)

        _isTokenSavedState.value = isTokenSaved
    }

    fun getErrorMessage(context: Context, errorMsg: String, errorCode: Int): String {

        return when (errorCode) {
            Constants.ErrorCode.NO_INTERNET_CONNECTION -> {
                context.resources.getString(R.string.no_internet_conn_error)
            }
            Constants.ErrorCode.INVALID_CREDENTIALS -> {
                context.resources.getString(R.string.invalid_username_or_password)
            }

            else -> {

                errorMsg.ifEmpty { context.resources.getString(R.string.unknown_error) }

            }
        }

    }

}