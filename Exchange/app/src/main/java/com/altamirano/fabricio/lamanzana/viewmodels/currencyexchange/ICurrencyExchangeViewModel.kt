package com.altamirano.fabricio.lamanzana.viewmodels.currencyexchange

interface ICurrencyExchangeViewModel {
     fun addListenerExchange()
     fun removeListener()
     fun onCoinValeChange(source: String)
     fun onEurosValeChange(euros: String)
}