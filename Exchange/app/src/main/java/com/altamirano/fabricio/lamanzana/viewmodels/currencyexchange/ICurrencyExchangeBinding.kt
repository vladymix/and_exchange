package com.altamirano.fabricio.lamanzana.viewmodels.currencyexchange

import com.altamirano.fabricio.lamanzana.entities.Coin
import com.github.mikephil.charting.data.LineData
import java.util.ArrayList

interface ICurrencyExchangeBinding {

    fun loadLastExchage(coin: Coin?)
    fun loadChart(lineData: LineData)
    fun setEurosText(txtEuros: String)
    fun setCoinValueText(txtCoin: String)
    fun loadCoins(coins: ArrayList<Coin>)
}