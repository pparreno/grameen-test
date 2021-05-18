package com.pjpsystems.grameentest.architecture.room.dao

import androidx.room.*
import com.pjpsystems.grameentest.data.room.Country

@Dao
interface CountryDao {
    @Insert
    suspend fun insertAll(vararg countries: Country)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun createAll(objects: List<Country>)

    @Delete
    suspend fun delete(country: Country)

    @Query("SELECT * FROM country")
    suspend fun getAll(): List<Country>
}