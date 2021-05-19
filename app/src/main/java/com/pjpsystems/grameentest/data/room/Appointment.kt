package com.pjpsystems.grameentest.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Appointment(
    @ColumnInfo(name = "country_iso")
    @PrimaryKey(autoGenerate = true) val uuid: Int,
    val title: String,
    val desc: String,
    val date: String,
    val startTime: String,
    val endTime: String,
    val partyName: String
)
