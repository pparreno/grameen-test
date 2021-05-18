package com.pjpsystems.grameentest.architecture.networking

import com.pjpsystems.grameentest.data.retrofit.Holiday
import retrofit2.http.GET
import retrofit2.http.Path

interface WorldwidePublicHolidayAPIService {

    @GET("api/v2/PublicHolidays/{year}/{country_code}")
    suspend fun getMovies(
        @Path("year") year: String,
        @Path("country_code") country_code: String
    ): List<Holiday>

}