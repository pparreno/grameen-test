package com.pjpsystems.grameentest.architecture.repository

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Room
import com.pjpsystems.grameentest.architecture.room.database.AppDatabase
import com.pjpsystems.grameentest.data.room.Country

class CountryRepository private constructor(context: Context) {
    private var db: AppDatabase? = null

    companion object {

        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        private var instance: CountryRepository? = null

        fun getInstance(context: Context): CountryRepository? {
            if (instance == null) {
                synchronized(CountryRepository::class.java) {
                    if (instance == null) {
                        instance = CountryRepository(context)
                    }
                }
            }
            return instance
        }
    }

    init {
        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        ).build()
    }



    suspend fun insertCountries(listCountries: List<Country>){
        db?.countryDao()!!.createAll(listCountries)
    }

}