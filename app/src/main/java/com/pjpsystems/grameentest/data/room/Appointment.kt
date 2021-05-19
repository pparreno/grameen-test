package com.pjpsystems.grameentest.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Appointment(
    val title: String,
    val desc: String,
    val date: String,
    val startTime: String,
    val endTime: String,
    val partyName: String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}
