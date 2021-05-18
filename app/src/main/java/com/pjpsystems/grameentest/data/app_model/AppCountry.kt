package com.pjpsystems.grameentest.data.app_model

import com.google.gson.annotations.SerializedName

data class AppCountry(
    @SerializedName("country_iso")
    var iso: String,
    @SerializedName("country_name")
    var name: String
)