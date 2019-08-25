package com.altamirano.fabricio.lamanzana.viewmodels.countries

import com.altamirano.fabricio.lamanzana.entities.Country

interface ICountriesBinding {

    fun loadCountries(allCountries: ArrayList<Country>)
}