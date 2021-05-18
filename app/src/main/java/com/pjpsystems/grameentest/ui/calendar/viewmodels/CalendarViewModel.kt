package com.pjpsystems.grameentest.ui.calendar.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.pjpsystems.grameentest.architecture.repository.HolidayRepository
import kotlinx.coroutines.Dispatchers

class CalendarViewModel(application: Application) : AndroidViewModel(application) {

    fun getHolidays(year: String, countryCode: String) = liveData(Dispatchers.IO) {
        emit(HolidayRepository.getInstance().retrieveHolidaysForSelectedYearAndCountry(year, countryCode))
    }
}