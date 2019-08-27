package com.altamirano.fabricio.lamanzana.viewmodels.countriescompany

import com.altamirano.fabricio.lamanzana.app.AppCompany
import com.altamirano.fabricio.lamanzana.entities.Country
import com.altamirano.fabricio.lamanzana.services.database.DataBase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class CountriesCompanyViewModel(val view: ICountriesCompanyBinding) : ICountriesCompanyViewModel, ValueEventListener {

    override fun onCancelled(p0: DatabaseError) {
        this.view.loadCountries(ArrayList())
    }

    override fun onDataChange(p0: DataSnapshot) {
        AppCompany.company.countries.clear()
        for (item in p0.children) {
            val i = item.getValue(Country::class.java)
            if (i != null) {
                AppCompany.company.countries.add(i)
            }
        }

        this.view.loadCountries(AppCompany.company.countries)
    }

    override fun startListener() {
        DataBase.addCountryListener(AppCompany.company, this)
    }

    override fun removeListener() {
        DataBase.removeCountryListener(AppCompany.company, this)
    }

}