package com.pjpsystems.grameentest.data.room

import androidx.room.Embedded
import androidx.room.Relation

data class CountryWithUsers(
    @Embedded val country: Country,
    @Relation(
        parentColumn = "country_iso",
        entityColumn = "country_iso"
    )
    val users : List<User>
)
