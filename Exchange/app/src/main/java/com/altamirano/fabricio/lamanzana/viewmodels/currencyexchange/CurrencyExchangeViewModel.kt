package com.altamirano.fabricio.lamanzana.viewmodels.currencyexchange

import android.graphics.Color
import android.text.TextUtils
import com.altamirano.fabricio.lamanzana.app.AppCustomer
import com.altamirano.fabricio.lamanzana.entities.Coin
import com.altamirano.fabricio.lamanzana.services.database.DataBase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class CurrencyExchangeViewModel(val view: ICurrencyExchangeBinding) : ICurrencyExchangeViewModel,
    ValueEventListener {


    private var exchange: Double = 0.0

    override fun onCancelled(p0: DatabaseError) {

    }

    override fun onDataChange(p0: DataSnapshot) {
        val dsCoins = p0.child(DataBase.DT_COINS)
        val coins = ArrayList<Coin>()
        for (item in dsCoins.children) {
            val i = item.getValue(Coin::class.java)
            i?.let {
                coins.add(it)
            }
        }
        this.loadExchange(coins.lastOrNull())
        this.view.loadLastExchage(coins.lastOrNull())
        this.view.loadCoins(coins)
        this.loadDataChart(coins)
    }

    private fun loadDataChart(coins: ArrayList<Coin>) {

        if (coins.size > 0) {
            val entries = ArrayList<Entry>()
            var counter = 1f

            for (coin in coins) {
                counter += 1f
                entries.add(Entry(counter, coin.exchange.toFloat()))
            }

            val dataset = LineDataSet(entries, coins[0].code)

            dataset.fillColor = Color.BLUE
            dataset.setDrawFilled(true)

            dataset.color = Color.BLUE
            dataset.lineWidth = 2f

            dataset.valueTextSize = 10f

            dataset.circleRadius = 5f
            dataset.setCircleColor(Color.BLUE)

            val lineData = LineData(dataset)

            this.view.loadChart(lineData)
        } else {
            this.view.loadChart(LineData())
        }
    }

    override fun addListenerExchange() {
        DataBase.addCountryListener(
            AppCustomer.company.code,
            AppCustomer.countryPos.toString(),
            this
        )
    }

    override fun removeListener() {
        DataBase.removeCountryListener(
            AppCustomer.company.code,
            AppCustomer.countryPos.toString(),
            this
        )
    }

    override fun onCoinValeChange(source: String) {
        try {
            val numCoin = source.asDouble() / exchange
            this.view.setEurosText(numCoin.asString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onEurosValeChange(euros: String) {
        try {
            val numCoin = euros.asDouble() * exchange
            this.view.setCoinValueText(numCoin.asString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadExchange(coin: Coin?) {
        coin?.let {
            this.exchange = it.exchange
        }
    }

    fun String.asDouble(): Double {
        if (TextUtils.isEmpty(this)) {
            return 0.0
        }
        try {
            return this.replace(",",".").toDouble()
        } catch (e: Exception) {
            return 0.0
        }

    }

    fun Double.asString(): String {
        if (this.isInfinite() || this.isNaN()) {
            return "0,0"
        } else {
            try {
                return String.format("%.2f", this).replace(".", ",")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return "0,0"
    }
}