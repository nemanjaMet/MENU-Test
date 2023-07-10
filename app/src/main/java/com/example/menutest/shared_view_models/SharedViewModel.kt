package com.example.menutest.shared_view_models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.LoginRequest
import com.example.data.VenueTest
import com.example.network.NetworkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar
import kotlin.random.Random

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

    fun getListOfVenues(): ArrayList<VenueTest> {
        val listOfVenues = arrayListOf<VenueTest>()

        fun roundTheNumber(numInDouble: Float): String {

            return "%.2f".format(numInDouble)

        }

        for (i in 0..49) {

            val distance = Random.nextInt(1,2000)
            val distanceText = if (distance >= 1000) "${roundTheNumber(distance / 1000f)}km" else "${distance}m"
            val startWorkingTime = Random.nextInt(6,20)
            val endWorkingTime = Random.nextInt(startWorkingTime + 1,24)
            val workingTimeText = "${if (startWorkingTime < 10) "0$startWorkingTime" else "$startWorkingTime"}:00 - ${if (endWorkingTime == 24) "00" else "$endWorkingTime" }:00"

            val currentHours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            Log.d("currentHoursTest", "currentHours: $currentHours")

            val venue = VenueTest(
                "Ocean Drive Miami",
                distanceText,
                "12 Belgard Road, Tallaght, Miami",
                workingTimeText,
                currentHours in startWorkingTime until endWorkingTime
            )

            listOfVenues.add(venue)

        }

        return listOfVenues
    }

}