package com.pjpsystems.grameentest.data.model

import com.google.gson.annotations.SerializedName

class AppCountry {
    @SerializedName("country_iso")
    lateinit var iso: String
    @SerializedName("country_name")
    lateinit var name: String
}
