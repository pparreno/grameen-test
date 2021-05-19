package com.pjpsystems.grameentest.architecture.repository

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Room
import com.pjpsystems.grameentest.architecture.room.database.AppDatabase
import com.pjpsystems.grameentest.data.room.Appointment

class AppointmentRepository private constructor(context: Context) {

    private var db: AppDatabase? = null

    companion object {

        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        private var instance: AppointmentRepository? = null

        fun getInstance(context: Context): AppointmentRepository {
            if (instance == null) {
                synchronized(AppointmentRepository::class.java) {
                    if (instance == null) {
                        instance = AppointmentRepository(context)
                    }
                }
            }
            return instance!!
        }
    }

    init {
        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        ).build()
    }


    suspend fun insertCountries(listAppointments: List<Appointment>) {
        return db?.appointmentDao()!!.createAll(listAppointments)
    }

}