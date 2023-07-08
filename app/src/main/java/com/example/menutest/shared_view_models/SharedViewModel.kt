package com.example.menutest.shared_view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.LoginRequest
import com.example.network.NetworkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

    init {
        autoHideSplash()
    }

    var isSplashVisible: MutableLiveData<Boolean> = MutableLiveData(true)

    fun hideSplashScreen() {
        if (isSplashVisible.value == true)
            isSplashVisible.postValue(false)
    }

    private fun autoHideSplash() {
        viewModelScope.launch {
            delay(2000)
            hideSplashScreen()
        }
    }

    fun testLogin() {

        viewModelScope.launch {

            val response = NetworkApi.retrofitService.executeLogin(LoginRequest("test@testmenu.app", "test1234"))

            val test = 0
        }

    }

}