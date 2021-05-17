package com.pjpsystems.grameentest.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @ColumnInfo(name = "employee_id")
    @PrimaryKey val uid: String,
    val name: String,
    val country_iso: String
)
