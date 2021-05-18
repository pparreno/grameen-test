package com.pjpsystems.grameentest.data.retrofit

import com.google.gson.annotations.SerializedName

class Holiday {
    lateinit var date: String
    var localName: String? = null
    lateinit var name: String
    lateinit var countryCode: String
    @SerializedName("fixed")
    var isFixed: Boolean = false
    @SerializedName("global")
    var isGlobal: Boolean = false
}