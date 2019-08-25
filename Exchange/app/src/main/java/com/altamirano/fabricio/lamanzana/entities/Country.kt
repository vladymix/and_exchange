package com.altamirano.fabricio.lamanzana.entities

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Country(var code: String = "",var name: String = "", var coins: ArrayList<Coin>? = ArrayList())