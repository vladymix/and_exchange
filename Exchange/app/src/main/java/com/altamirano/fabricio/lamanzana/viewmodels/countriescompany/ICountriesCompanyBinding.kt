package com.altamirano.fabricio.lamanzana.viewmodels.countriescompany

import com.altamirano.fabricio.lamanzana.entities.Country

interface ICountriesCompanyBinding {

    fun loadCountries(items: ArrayList<Country>)
}