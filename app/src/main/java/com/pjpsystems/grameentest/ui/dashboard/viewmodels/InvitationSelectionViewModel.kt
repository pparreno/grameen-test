package com.pjpsystems.grameentest.ui.dashboard.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pjpsystems.grameentest.architecture.repository.UserRepository
import com.pjpsystems.grameentest.data.room.User
import kotlinx.coroutines.launch

class InvitationSelectionViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository: UserRepository = UserRepository.getInstance(application)

    var oRoomUsers: MutableLiveData<List<User>> = MutableLiveData()

    fun retrieveUsers(){
        viewModelScope.launch {
            val result = userRepository.retrieveUsers()
            oRoomUsers.postValue(result)
        }
    }
}