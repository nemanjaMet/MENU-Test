package com.example.server

import com.example.data.Images
import com.example.data.Venue
import com.google.gson.Gson
import java.util.Calendar
import kotlin.random.Random

object MockDataHelper {

    fun getListOfVenuesJson(): String {
        val listOfVenues = createListOfVenues()

        return Gson().toJson(listOfVenues)
    }


    private fun createListOfVenues(): ArrayList<Venue> {
        val listOfVenues = arrayListOf<Venue>()
        val imageUrls = getImageUrls()

        val name = "Ocean Drive Miami"
        var distance = 0
        val location = "Belgard Road, Tallaght Miami"
        val welcomeMessage = "Welcome to Poke Bar"
        val description = "Poke Bar makes it easy to customize your bowl with endless toppings, proteins, mix-in and more."

        for (i in 0..49) {

            // distance
            distance += Random.nextInt(50,300)
            val distanceText = if (distance >= 1000) "${roundTheNumber(distance / 1000f)}km" else "${distance}m"

            // working hours
            val startWorkingTime = Random.nextInt(6,20)
            val endWorkingTime = Random.nextInt(startWorkingTime + 1,24)
            val workingTimeText = "${if (startWorkingTime < 10) "0$startWorkingTime" else "$startWorkingTime"}:00 - ${if (endWorkingTime == 24) "00" else "$endWorkingTime" }:00"

            val currentHours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            //Log.d("currentHoursTest", "currentHours: $currentHours")

            val venue = Venue(
                "$name ${i + 1}",
                distanceText,
                "${i + 1} $location",
                workingTimeText,
                currentHours in startWorkingTime until endWorkingTime,
                welcomeMessage,
                description,
                Images(imageUrls[i % 9])
            )

            listOfVenues.add(venue)

        }

        return listOfVenues
    }

    private fun roundTheNumber(numInDouble: Float): String {
        return "%.2f".format(numInDouble)
    }

    private fun getImageUrls(): ArrayList<String> {

        val listOfUrls = arrayListOf<String>()

        listOfUrls.add("https://raw.githubusercontent.com/nemanjaMet/MENU-Test/master/images/Image%201.webp")
        listOfUrls.add("https://raw.githubusercontent.com/nemanjaMet/MENU-Test/master/images/Image%202.webp")
        listOfUrls.add("https://raw.githubusercontent.com/nemanjaMet/MENU-Test/master/images/Image%203.webp")
        listOfUrls.add("https://raw.githubusercontent.com/nemanjaMet/MENU-Test/master/images/Image%204.webp")
        listOfUrls.add("https://raw.githubusercontent.com/nemanjaMet/MENU-Test/master/images/Image%205.webp")
        listOfUrls.add("https://raw.githubusercontent.com/nemanjaMet/MENU-Test/master/images/Image%206.webp")
        listOfUrls.add("https://raw.githubusercontent.com/nemanjaMet/MENU-Test/master/images/Image%207.webp")
        listOfUrls.add("https://raw.githubusercontent.com/nemanjaMet/MENU-Test/master/images/Image%208.webp")
        listOfUrls.add("https://raw.githubusercontent.com/nemanjaMet/MENU-Test/master/images/Image%209.webp")

        return listOfUrls

    }

}