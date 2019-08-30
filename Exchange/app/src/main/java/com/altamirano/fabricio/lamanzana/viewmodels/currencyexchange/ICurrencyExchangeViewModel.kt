package com.altamirano.fabricio.lamanzana.viewmodels.currencyexchange

import com.altamirano.fabricio.lamanzana.entities.Coin
import java.util.ArrayList

interface ICurrencyExchangeViewModel {
     fun addListenerExchange()
     fun removeListener()
     fun onCoinValeChange(source: String)
     fun onEurosValeChange(euros: String)
}