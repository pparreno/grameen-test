package com.pjpsystems.grameentest.ui.configuration.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pjpsystems.grameentest.data.model.AppCountry
import com.pjpsystems.grameentest.data.model.AppUser
import com.pjpsystems.grameentest.utils.ParseUtils
import timber.log.Timber

class LoadScreenViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var usersListLiveData: MutableLiveData<List<AppUser>>
    lateinit var countriesListLiveData: MutableLiveData<List<AppCountry>>
    val gson = Gson()

    fun retrieveDefaultValues(){
        retrieveDefaultCountries()
        retrieveDefaultUsers()
    }

    private fun retrieveDefaultCountries(){
        val jsonFileString = ParseUtils.getJsonDataFromAsset(getApplication(), "countries.json")
        Timber.i("data: %s", jsonFileString)
        val listCountryType = object : TypeToken<List<AppCountry>>() {}.type
        val countries: List<AppCountry> = gson.fromJson(jsonFileString, listCountryType)
        countriesListLiveData = MutableLiveData()
        countriesListLiveData.postValue(countries)
    }

    private fun retrieveDefaultUsers() {
        val jsonFileString = ParseUtils.getJsonDataFromAsset(getApplication(), "users.json")
        Timber.i("data: %s", jsonFileString)
        val listUserType = object : TypeToken<List<AppUser>>() {}.type
        val users: List<AppUser> = gson.fromJson(jsonFileString, listUserType)
        usersListLiveData = MutableLiveData()
        usersListLiveData.postValue(users)
    }
}