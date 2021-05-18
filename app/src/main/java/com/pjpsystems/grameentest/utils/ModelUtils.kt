package com.pjpsystems.grameentest.utils

import com.pjpsystems.grameentest.data.app_model.AppCountry
import com.pjpsystems.grameentest.data.app_model.AppUser
import com.pjpsystems.grameentest.data.room.Country
import com.pjpsystems.grameentest.data.room.User

object ModelUtils {

    fun convertRoomCountryListFromAppCountryList(appCountryList: List<AppCountry>): List<Country>{
        val countryList = ArrayList<Country>()
        for(appCountry:AppCountry in appCountryList) {
            val country = Country(appCountry.iso, appCountry.name)
            countryList.add(country)
        }
        return countryList
    }

    fun convertRoomUserListFromAppUserList(appUserList: List<AppUser>): List<User>{
        val userList = ArrayList<User>()
        for(appUser:AppUser in appUserList) {
            val user = User(appUser.uid, appUser.name, appUser.country_iso)
            userList.add(user)
        }
        return userList
    }
}