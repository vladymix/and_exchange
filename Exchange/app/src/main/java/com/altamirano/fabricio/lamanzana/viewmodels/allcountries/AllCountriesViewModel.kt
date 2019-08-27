package com.altamirano.fabricio.lamanzana.viewmodels.allcountries

import com.altamirano.fabricio.lamanzana.services.ServiceCountries

class AllCountriesViewModel(val view:IAllCountriesBinding) :IAllCountriesViewModel {

    override fun loadCountries() {
        view.loadCountries(ServiceCountries.getAllCountries())
    }
}