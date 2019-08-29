package com.altamirano.fabricio.lamanzana.viewmodels.currencyexchange

import com.altamirano.fabricio.lamanzana.entities.Coin

interface ICurrencyExchangeBinding {
     fun loadExchange(arrayList: ArrayList<Coin>?)
}