package com.pjpsystems.grameentest.architecture.room.dao

import androidx.room.*
import com.pjpsystems.grameentest.data.room.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertAll(vararg users: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun createAll(objects: List<User>)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

}