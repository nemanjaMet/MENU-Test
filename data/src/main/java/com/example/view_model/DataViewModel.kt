package com.example.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.data.LoginRequest
import com.example.data.VenueTest
import com.example.network.NetworkApi
import com.example.network.NetworkApiService
import kotlinx.coroutines.launch
import java.util.Calendar
import kotlin.random.Random

class DataViewModel : ViewModel() {

    private var listOfVenues: ArrayList<VenueTest>
    private var loginToken: MutableLiveData<String> = MutableLiveData()

    init {
        listOfVenues = createListOfVenues()
    }

    private fun createListOfVenues(): ArrayList<VenueTest> {
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
                currentHours in startWorkingTime until endWorkingTime,
                "Welcome to Poke Bar",
                "Poke Bar makes it easy to customize your bowl with endless toppings, proteins, mix-in and more."
            )

            listOfVenues.add(venue)

        }

        return listOfVenues
    }

    fun getListOfVenues(): ArrayList<VenueTest> {
        return listOfVenues
    }

    fun getVenue(position: Int): VenueTest {
        return listOfVenues[position]
    }

   /* fun login(loginRequest: LoginRequest) {

        viewModelScope.launch {
            val response = NetworkApi.retrofitService.executeLogin().body()
            val test = 0
        }

    }*/

}