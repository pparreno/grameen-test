package com.pjpsystems.grameentest.architecture.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.pjpsystems.grameentest.data.room.Country

@Dao
interface CountryDao {
    @Insert
    fun insertAll(vararg countries: Country)

    @Delete
    fun delete(country: Country)

    @Query("SELECT * FROM country")
    fun getAll(): List<Country>
}