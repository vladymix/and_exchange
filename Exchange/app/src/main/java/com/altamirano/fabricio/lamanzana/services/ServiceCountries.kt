package com.altamirano.fabricio.lamanzana.services

import android.content.Context
import com.altamirano.fabricio.lamanzana.entities.Country
import java.util.*
import kotlin.collections.ArrayList

object ServiceCountries {

    fun getAllCountries(): ArrayList<Country> {
        val list = ArrayList<Country>()

        list.add(Country("AFG","Afganistan", ArrayList()))
        list.add(Country("ARG","Argentina", ArrayList()))
        list.add(Country("AUS","Australia", ArrayList()))
        list.add(Country("BGR","Bulgaria", ArrayList()))
        list.add(Country("BOL","Bolivia", ArrayList()))
        list.add(Country("CHL","Chile", ArrayList()))
        list.add(Country("COL","Colombia", ArrayList()))
        list.add(Country("DOM","República dominicana", ArrayList()))
        list.add(Country("ECU","Ecuador", ArrayList()))
        list.add(Country("GTM","Guatemala", ArrayList()))
        list.add(Country("PER","Perú", ArrayList()))
        list.add(Country("PRI","Puerto rico", ArrayList()))
        list.add(Country("PRY","Paraguay", ArrayList()))


        list.add(Country("ROU","Rumania", ArrayList()))
        list.add(Country("SLV","El Salvador", ArrayList()))
        list.add(Country("UKR","Ucrania", ArrayList()))
        list.add(Country("URY","Uruguay", ArrayList()))
        list.add(Country("USA","Estados unidos", ArrayList()))
        list.add(Country("VEN","Venezuela", ArrayList()))

        return list
    }

    fun getIdImage(ctx:Context, code:String):Int{
        val id_code = "ic_"+code.toLowerCase()
       return ctx.resources.getIdentifier(id_code,"drawable",ctx.packageName)
    }


}