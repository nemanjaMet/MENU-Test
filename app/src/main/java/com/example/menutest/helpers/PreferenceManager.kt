package com.example.menutest.helpers

import android.content.Context
import android.content.SharedPreferences
import com.example.menutest.constants.Constants

class PreferenceManager(context: Context) {

    private var preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(context.packageName + "." + Constants.PREF_NAME, Context.MODE_PRIVATE)
    }

    /*fun init(context: Context) {
        if (!isInitialized())
            preferences = context.getSharedPreferences(context.packageName + "." + Constants.PREF_NAME, Context.MODE_PRIVATE)
    }

    private fun isInitialized() : Boolean {
        return ::preferences.isInitialized
    }*/

    fun setStringValue(key: String, value: String) { preferences.edit().putString(key, value).apply() }

    fun getStringValue(key: String, defaultValue: String) : String? { return preferences.getString(key, defaultValue) }

    fun removePreference(key: String) {
        preferences.edit().remove(key).apply()
    }

    fun containsKey(key: String) : Boolean {
        return preferences.contains(key)
    }

}