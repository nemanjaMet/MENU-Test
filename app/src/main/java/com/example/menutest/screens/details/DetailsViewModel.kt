package com.example.menutest.screens.details

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.menutest.constants.Constants
import com.example.menutest.helpers.PreferenceManager

class DetailsViewModel : ViewModel() {

    private val _isDataLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isDataLoading: MutableLiveData<Boolean> get() = _isDataLoading

    fun logout(context: Context) {
        val preferenceManager = PreferenceManager(context)
        preferenceManager.removePreference(Constants.PreferenceName.ACCESS_TOKEN)
    }

}