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
    lateinit var company: Company

    private val formater = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH)
    private val formaterPreview = SimpleDateFormat("dd MMM yyyy, HH:mm:ss", Locale.ENGLISH)
//    private val formaterPreview = SimpleDateFormat.getDateInstance()

    fun loadCompany(user: FirebaseUser, listener: IServiceResponse) {
        DataBase.searchCompany(user, object : DataBaseResult<Company> {
            override fun onResult(t: Company?) {
                company = t!!
                listener.companyResult(company)
            }
        })
    }

    fun addCoin(selected: Country, codeCoin: String, symbol: String, date: Date, change: Double) {
        // Write a message to the database
        val toServer = searchOrCreateCountry(selected)

        toServer.run {
            val coin = Coin()
            coin.code = codeCoin
            coin.symbol = symbol
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

    fun getDatePreview(date: Date): String {
        return formaterPreview.format(date)
    }

    fun saveCompany(name: String, direcction: String, postalcode: Int) {
        company.name = name
        company.direction = direcction
        company.postalCode = postalcode

        DataBase.updateCompany(company)
    }

    private fun searchOrCreateCountry(search: Country): Country {
        var country: Country?

        country = company.countries.find { t ->
            t.code.equals(search.code)
        }

        if (country == null) {
            country = Country(search.code, search.codeCoin, search.symbol, search.name, ArrayList())
            company.countries.add(country)
        }

        return country;
    }

    fun deleteCoin(country: Country) {

        DataBase.deleteCountry(company, country)
    }

}