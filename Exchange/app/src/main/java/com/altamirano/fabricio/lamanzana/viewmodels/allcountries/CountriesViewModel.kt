package com.altamirano.fabricio.lamanzana.viewmodels.allcountries

import com.altamirano.fabricio.lamanzana.services.ServiceCountries

class CountriesViewModel(val view:ICountriesBinding) :ICountriesViewModel {

    override fun loadCountries() {
        view.loadCountries(ServiceCountries.getAllCountries())
    }
}