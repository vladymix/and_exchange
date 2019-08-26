package com.altamirano.fabricio.lamanzana.entities

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Company(
    var code: String = "",
    var email: String = "",
    var name: String = "",
    var direction: String = "",
    var postalCode: Int = 0,
    var countries: ArrayList<Country> = ArrayList()
)