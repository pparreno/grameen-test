package com.pjpsystems.grameentest.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Country(
    @ColumnInfo(name = "country_iso")
    @PrimaryKey val iso: String,
    val name: String
)
