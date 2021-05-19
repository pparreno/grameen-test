package com.pjpsystems.grameentest.architecture.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pjpsystems.grameentest.data.room.Appointment
import com.pjpsystems.grameentest.data.room.Country

interface AppointmentDao {
    @Insert
    suspend fun insertAll(vararg countries: Appointment)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun createAll(objects: List<Appointment>)

    @Delete
    suspend fun delete(country: Appointment)

    @Query("SELECT * FROM appointment")
    suspend fun getAll(): List<Appointment>
}