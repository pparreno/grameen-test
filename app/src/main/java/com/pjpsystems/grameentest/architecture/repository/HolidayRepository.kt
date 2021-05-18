package com.pjpsystems.grameentest.architecture.repository

import android.annotation.SuppressLint
import com.pjpsystems.grameentest.architecture.networking.WorldwidePublicHolidayAPIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Suppress("JoinDeclarationAndAssignment")
class HolidayRepository {

    private val baseUrl: String = "https://date.nager.at/"
    private val retrofit: Retrofit
    private val service: WorldwidePublicHolidayAPIService

    companion object {

        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        private var instance: HolidayRepository? = null

        fun getInstance(): HolidayRepository {
            if (instance == null) {
                synchronized(HolidayRepository::class.java) {
                    if (instance == null) {
                        instance = HolidayRepository()
                    }
                }
            }
            return instance!!
        }
    }

    init {

        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(WorldwidePublicHolidayAPIService::class.java)
    }


    suspend fun retrieveHolidaysForSelectedYearAndCountry(year: String, countryCode: String) =
        service.getMovies(year, countryCode)

}