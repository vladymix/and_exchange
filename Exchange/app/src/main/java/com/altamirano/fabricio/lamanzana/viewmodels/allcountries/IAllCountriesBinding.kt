package com.altamirano.fabricio.lamanzana.viewmodels.allcountries

import com.altamirano.fabricio.lamanzana.entities.Country

interface IAllCountriesBinding {
    fun loadCountries(allCountries: ArrayList<Country>)
}