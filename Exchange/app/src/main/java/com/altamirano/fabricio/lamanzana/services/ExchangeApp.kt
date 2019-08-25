package com.altamirano.fabricio.lamanzana.services

import com.altamirano.fabricio.lamanzana.entities.Coin
import com.altamirano.fabricio.lamanzana.entities.Country
import com.altamirano.fabricio.lamanzana.entities.Exchange
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object ExchangeApp {

    val formater = SimpleDateFormat("dd-MM-YYYY HH:mm:ss", Locale.ENGLISH)

    fun asString(number: Double): String {
        if (number.isInfinite() || number.isNaN()) {
            return "0.0"
        } else {
            try {
                return String.format("%2f", number).replace(",", ".")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return "0.0"
    }

    var exchange: Exchange =
        Exchange(ArrayList())

    fun addCoin(selected: Country, codeCoin: String, date: Date, change: Double) {
        // Write a message to the database
        val toServer = getOrCreateCountry(selected.code, selected.name)

        toServer.run {
            val coin = Coin()
            coin.code = codeCoin
            coin.dateUpdate = getStringDate(date)
            coin.exchange = change
            coins?.add(coin)
        }

        FirebaseDatabase.getInstance().getReference().child("Exchange").setValue(exchange)
    }

    fun getCountry(code: String): Country? {
        var country: Country?

        country = exchange.countries?.find { t ->
            t.code.equals(code)
        }
        return country;
    }

    private fun getOrCreateCountry(code: String, name: String): Country {
        var country: Country?

        country = exchange.countries?.find { t ->
            t.code.equals(code)
        }

        if (country == null) {
            country = Country(code, name, ArrayList())
            exchange.countries!!.add(country)
        }

        return country;
    }


    fun getLastExchange(countryCode: String, codeCoin: String): Coin? {
        val country = getCountry(countryCode)
        return country?.coins!!.lastOrNull { c -> c.code.equals(codeCoin) }
    }

    fun getStringDate(date: Date): String {
        return formater.format(date)
    }

    fun getDateString(date: String): Date? {
        return formater.parse(date)
    }

}