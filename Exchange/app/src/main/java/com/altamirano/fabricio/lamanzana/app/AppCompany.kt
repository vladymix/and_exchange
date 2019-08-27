package com.altamirano.fabricio.lamanzana.app

import com.altamirano.fabricio.lamanzana.entities.*
import com.altamirano.fabricio.lamanzana.services.IServiceResponse
import com.altamirano.fabricio.lamanzana.services.database.DataBase
import com.altamirano.fabricio.lamanzana.services.database.DataBaseResult
import com.google.firebase.auth.FirebaseUser
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object AppCompany {

    lateinit var countrySelected: Country
    val formater = SimpleDateFormat("dd-MM-YYYY HH:mm:ss", Locale.ENGLISH)

    lateinit var company: Company

    fun loadCompany(user: FirebaseUser, listener: IServiceResponse) {
        DataBase.searchCompany(user, object : DataBaseResult<Company> {
            override fun onResult(t: Company?) {
                company = t!!
                listener.companyResult(company)
            }
        })
    }

    fun addCoin(selected: Country, codeCoin: String, date: Date, change: Double) {
        // Write a message to the database
        val toServer = searchOrCreateCountry(
            selected.code,
            selected.name
        )

        toServer.run {
            val coin = Coin()
            coin.code = codeCoin
            coin.dateUpdate = getStringDate(date)
            coin.exchange = change
            coins?.add(coin)
        }

        DataBase.updateCountries(company.code, company.countries)
    }

    fun getCountry(code: String): Country? {
        val country: Country?

        country = company.countries.find { t ->
            t.code.equals(code)
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

    private fun searchOrCreateCountry(code: String, name: String): Country {
        var country: Country?

        country = company.countries.find { t ->
            t.code.equals(code)
        }

        if (country == null) {
            country = Country(code, name, ArrayList())
            company.countries.add(country)
        }

        return country;
    }

    fun saveCompany(name: String, direcction: String, postalcode: Int) {
        company.name = name
        company.direction = direcction
        company.postalCode = postalcode

        DataBase.updateCompany(company)
    }

}