package com.pjpsystems.grameentest.architecture.helpers

import com.pjpsystems.grameentest.data.app_model.AppUser
import com.pjpsystems.grameentest.data.room.Appointment

object SchedulerHelper {

    var selectedUsers: AppUser? = null
    var selectedDate: String? = null
    var startTime: String? = null
    var endTime: String? = null
    var title: String? = null
    var desc: String? = null

    fun buildAppointment(): Appointment {
        return Appointment(
            title!!,
            desc!!,
            selectedDate!!,
            startTime!!,
            endTime!!,
            selectedUsers!!.name
        )
    }

    fun clearValues() {
        selectedUsers = null
        selectedDate = null
        startTime = null
        endTime = null
        title = null
        desc = null
    }


}