package com.pjpsystems.grameentest.data.model

import com.google.gson.annotations.SerializedName

data class AppUser(
    @SerializedName("employee_id")
    val uid: String,
    val name: String,
    val country_iso: String
)
