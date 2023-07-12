package com.example.menutest.shared_view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

}