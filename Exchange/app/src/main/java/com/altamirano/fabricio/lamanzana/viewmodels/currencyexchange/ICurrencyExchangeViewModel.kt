package com.altamirano.fabricio.lamanzana.viewmodels.currencyexchange

import com.altamirano.fabricio.lamanzana.entities.Country

interface ICurrencyExchangeViewModel {
     fun addListenerExchange(country: Country)
}