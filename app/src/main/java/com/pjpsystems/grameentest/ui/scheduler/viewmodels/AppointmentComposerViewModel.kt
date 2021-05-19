package com.pjpsystems.grameentest.ui.scheduler.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.pjpsystems.grameentest.architecture.repository.AppointmentRepository
import com.pjpsystems.grameentest.architecture.repository.HolidayRepository
import com.pjpsystems.grameentest.architecture.repository.UserRepository
import com.pjpsystems.grameentest.data.room.Appointment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppointmentComposerViewModel(application: Application) : AndroidViewModel(application) {

    private val appointmentRepository: AppointmentRepository = AppointmentRepository.getInstance(application)

    val resultLiveData: MutableLiveData<Unit> = MutableLiveData()

    fun getHolidays(year: String, countryCode: String) = liveData(Dispatchers.IO) {
        emit(HolidayRepository.getInstance().retrieveHolidaysForSelectedYearAndCountry(year, countryCode))
    }

    fun submitValidAppointments(validAppointments: List<Appointment>){
        viewModelScope.launch {
            val result = appointmentRepository.insertAppointments(validAppointments)
            resultLiveData.postValue(result)

        }
    }
}