package com.pjpsystems.grameentest.ui.configuration.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pjpsystems.grameentest.architecture.repository.CountryRepository
import com.pjpsystems.grameentest.architecture.repository.UserRepository
import com.pjpsystems.grameentest.data.app_model.AppCountry
import com.pjpsystems.grameentest.data.app_model.AppUser
import com.pjpsystems.grameentest.utils.ModelUtils
import com.pjpsystems.grameentest.utils.ParseUtils
import kotlinx.coroutines.launch
import timber.log.Timber

class LoadScreenViewModel(application: Application) : AndroidViewModel(application) {

    val gson = Gson()
    private val countryRepository: CountryRepository = CountryRepository.getInstance(application)!!
    private val userRepository: UserRepository = UserRepository.getInstance(application)!!

    var flagStoredDefaultCountries: MutableLiveData<Boolean> = MutableLiveData()
    var flagStoredDefaultUsers: MutableLiveData<Boolean> = MutableLiveData()

    fun retrieveDefaultValues() {
        retrieveDefaultCountries()
        retrieveDefaultUsers()
    }

    private fun retrieveDefaultCountries() {
        val jsonFileString = ParseUtils.getJsonDataFromAsset(getApplication(), "countries.json")
        Timber.i("data: %s", jsonFileString)
        val listCountryType = object : TypeToken<List<AppCountry>>() {}.type
        val countries: List<AppCountry> = gson.fromJson(jsonFileString, listCountryType)
        viewModelScope.launch {

            try {
                countryRepository.insertCountries(
                    ModelUtils.convertRoomCountryListFromAppCountryList(
                        countries
                    )
                )
                flagStoredDefaultCountries.postValue(true)
            } catch (e: Exception) {
                flagStoredDefaultCountries.postValue(false)
            }
        }

    }

    private fun retrieveDefaultUsers() {
        val jsonFileString = ParseUtils.getJsonDataFromAsset(getApplication(), "users.json")
        Timber.i("data: %s", jsonFileString)
        val listUserType = object : TypeToken<List<AppUser>>() {}.type
        val users: List<AppUser> = gson.fromJson(jsonFileString, listUserType)
        viewModelScope.launch {

            try {
                userRepository.insertUsers(ModelUtils.convertRoomUserListFromAppUserList(users))
                flagStoredDefaultUsers.postValue(true)
            } catch (e: Exception) {
                flagStoredDefaultUsers.postValue(false)
            }

        }
    }
}