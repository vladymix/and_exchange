package com.altamirano.fabricio.lamanzana.entities

import android.annotation.SuppressLint
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Country(var code: String = "",var name: String = "", var coins: ArrayList<Coin>? = ArrayList()){
    @SuppressLint("DefaultLocale")
    override  fun toString():String{
        return "${code.toLowerCase()} ${name.toLowerCase()}"
    }
}