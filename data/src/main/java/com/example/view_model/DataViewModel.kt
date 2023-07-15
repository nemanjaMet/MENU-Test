package com.example.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controller.DataController
import com.example.data.ResponseStatus
import com.example.data.VenueTest
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DataViewModel : ViewModel() {

    private val _listOfVenues: MutableLiveData<ArrayList<VenueTest>> = MutableLiveData()
    val listOfVenues get() = _listOfVenues
    private val _dataResponseStatus: MutableLiveData<DataResponseStatus> = MutableLiveData()
    val dataResponseStatus get() = _dataResponseStatus


    fun setListOfVenues() {

        _dataResponseStatus.value = DataResponseStatus.IN_PROGRESS

        viewModelScope.launch {
            // delay to fake loading ;; it was left on purpose
            delay(500)

            val response = DataController().getListOfVenues()

            when (response.status) {
                ResponseStatus.SUCCESS -> {
                    _listOfVenues.postValue(Gson().fromJson(response.data, Array<VenueTest>::class.java).toCollection(ArrayList()))
                    _dataResponseStatus.postValue(DataResponseStatus.SUCCESS)
                }
                ResponseStatus.CONNECTION_ERROR -> {
                    _dataResponseStatus.postValue(DataResponseStatus.CONNECTION_ERROR)
                }
                else -> {
                    _dataResponseStatus.postValue(DataResponseStatus.UNKNOWN_ERROR)
                }
            }

        }

    }

    fun clearData() {
        listOfVenues.value = null
        dataResponseStatus.value = null
    }

    fun getVenue(position: Int): VenueTest? {
        return listOfVenues.value?.get(position)
    }

}

enum class DataResponseStatus {
    IN_PROGRESS,
    SUCCESS,
    UNKNOWN_ERROR,
    CONNECTION_ERROR
}