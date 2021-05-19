package com.pjpsystems.grameentest.data.app_model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class AppUser(
    @SerializedName("employee_id")
    val uid: String,
    val name: String,
    val country_iso: String
): Serializable
