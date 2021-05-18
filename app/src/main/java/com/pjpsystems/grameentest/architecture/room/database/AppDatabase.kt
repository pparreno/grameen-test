package com.pjpsystems.grameentest.architecture.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pjpsystems.grameentest.architecture.room.dao.CountryDao
import com.pjpsystems.grameentest.architecture.room.dao.UserDao
import com.pjpsystems.grameentest.data.room.Country
import com.pjpsystems.grameentest.data.room.User


@Database(entities = [User::class, Country::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun countryDao(): CountryDao
}