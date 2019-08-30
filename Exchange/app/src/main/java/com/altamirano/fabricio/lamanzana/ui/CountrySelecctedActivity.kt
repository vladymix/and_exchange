package com.altamirano.fabricio.lamanzana.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.altamirano.fabricio.lamanzana.R
import com.altamirano.fabricio.lamanzana.adapters.CountryCoinsAdapter
import com.altamirano.fabricio.lamanzana.app.AppCompany
import com.altamirano.fabricio.lamanzana.app.AppCustomer
import com.altamirano.fabricio.lamanzana.entities.Country
import com.altamirano.fabricio.lamanzana.services.database.DataBase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_country_seleccted.*

class CountrySelecctedActivity : AppCompatActivity(), ValueEventListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_seleccted)

        listview.adapter = CountryCoinsAdapter(this, AppCustomer.company.countries)

        DataBase.addCountriesListener(AppCustomer.company.code, this)
    }

    override fun onCancelled(p0: DatabaseError) {

    }

    override fun onDataChange(p0: DataSnapshot) {
        AppCustomer.company.countries.clear()
        for (item in p0.children) {
            val i = item.getValue(Country::class.java)
            i?.let {
                AppCustomer.company.countries.add(it)
            }

        }
        listview.adapter = CountryCoinsAdapter(this, AppCustomer.company.countries)
    }

    override fun onDestroy() {
        DataBase.removeCountriesListener(AppCustomer.company.code, this)
        super.onDestroy()
    }
}
