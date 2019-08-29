package com.altamirano.fabricio.lamanzana.viewmodels.currencyexchange

import com.altamirano.fabricio.lamanzana.app.AppCustomer
import com.altamirano.fabricio.lamanzana.entities.Coin
import com.altamirano.fabricio.lamanzana.entities.Country
import com.altamirano.fabricio.lamanzana.services.database.DataBase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class CurrencyExchangeViewModel (val view:ICurrencyExchangeBinding):ICurrencyExchangeViewModel,
    ValueEventListener {

    override fun onCancelled(p0: DatabaseError) {

    }

    override fun onDataChange(p0: DataSnapshot) {
       val dsCoins = p0.child(DataBase.DT_COINS)
        val coins = ArrayList<Coin>()
        for (item in dsCoins.children) {
            val i = item.getValue(Coin::class.java)
            if (i != null) {
                coins.add(i)
            }
        }
        this.view.loadExchange(coins)
    }


    override fun addListenerExchange(country: Country) {
        DataBase.addCountryListener(AppCustomer.company.code, AppCustomer.countryPos.toString(),this)
    }
}