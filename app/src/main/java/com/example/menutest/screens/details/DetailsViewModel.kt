package com.example.menutest.screens.details

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.menutest.constants.Constants
import com.example.menutest.helpers.PreferenceManager

class DetailsViewModel : ViewModel() {

    fun logout(context: Context) {
        val preferenceManager = PreferenceManager(context)
        preferenceManager.removePreference(Constants.PreferenceName.LOGIN_TOKEN)
    }

}