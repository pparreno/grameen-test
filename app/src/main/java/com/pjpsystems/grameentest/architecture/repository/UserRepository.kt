package com.pjpsystems.grameentest.architecture.repository

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Room
import com.pjpsystems.grameentest.architecture.room.database.AppDatabase
import com.pjpsystems.grameentest.data.room.Country
import com.pjpsystems.grameentest.data.room.User

class UserRepository private constructor(context: Context){

    private var db: AppDatabase? = null

    companion object {

        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        private var instance: UserRepository? = null

        fun getInstance(context: Context): UserRepository? {
            if (instance == null) {
                synchronized(UserRepository::class.java) {
                    if (instance == null) {
                        instance = UserRepository(context)
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

    suspend fun insertUsers(listUsers: List<User>) {
        return db?.userDao()!!.createAll(listUsers)
    }

    suspend fun retrieveUsers(): List<User> {
        return db?.userDao()!!.getAll()
    }
}