package com.altamirano.fabricio.lamanzana.services

import android.content.Context
import com.altamirano.fabricio.lamanzana.entities.Country

object ServiceCountries {

    fun getAllCountries(): ArrayList<Country> {
        val list = ArrayList<Country>()

        list.add(Country("AFG","AFN","پول","Afganistan", ArrayList()))
        list.add(Country("ARG","ARS","$","Argentina", ArrayList()))
        list.add(Country("AUS","AUD","$","Australia", ArrayList()))
        list.add(Country("BGR","BGN","Лв","Bulgaria", ArrayList()))
        list.add(Country("BOL","BOB","Bs","Bolivia", ArrayList()))
        list.add(Country("CHL","CLP","$","Chile", ArrayList()))
        list.add(Country("COL","COP","$","Colombia", ArrayList()))
        list.add(Country("DOM","DOP","RD$","República dominicana", ArrayList()))
        list.add(Country("ECU","USD","$","Ecuador", ArrayList()))
        list.add(Country("GTM","GTQ ","Q","Guatemala", ArrayList()))
        list.add(Country("PER","PEN","S/.","Perú", ArrayList()))
        list.add(Country("PRI","USD","$","Puerto rico", ArrayList()))
        list.add(Country("PRY","PYG","₲","Paraguay", ArrayList()))
        list.add(Country("ROU","RON","RON","Rumania", ArrayList()))
        list.add(Country("SLV","USD","$","El Salvador", ArrayList()))
        list.add(Country("UKR","UAH","₴","Ucrania", ArrayList()))
        list.add(Country("URY","UYU","$","Uruguay", ArrayList()))
        list.add(Country("USA","USD","$","Estados unidos", ArrayList()))
        list.add(Country("VEN","VES","Bs.","Venezuela", ArrayList()))

        return list
    }

    fun getIdImage(ctx:Context, code:String):Int{
        val id_code = "ic_"+code.toLowerCase()
       return ctx.resources.getIdentifier(id_code,"drawable",ctx.packageName)
    }


}