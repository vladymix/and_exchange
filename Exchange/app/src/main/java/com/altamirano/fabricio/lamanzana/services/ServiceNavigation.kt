package com.altamirano.fabricio.lamanzana.services

import android.app.Activity
import android.content.Intent
import com.altamirano.fabricio.lamanzana.ui.CountriesActivity
import com.altamirano.fabricio.lamanzana.NewChangeActivity
import com.altamirano.fabricio.lamanzana.entities.Country
import com.altamirano.fabricio.lamanzana.ui.CompanyMainActivity
import com.altamirano.fabricio.lamanzana.ui.LoginActivity

object ServiceNavigation {

    lateinit var countrySeleccted:Country

    fun goToSearchCountry(activity: Activity) {
        val intent = Intent(activity, CountriesActivity::class.java)
        activity.startActivity(intent)
    }

    fun gotoLogin(activity: Activity) {
        val intent = Intent(activity, LoginActivity::class.java)
        activity.startActivity(intent)
    }

    fun goToCreateCoin(activity: Activity,country: Country) {
        countrySeleccted = country
        val intent = Intent(activity, NewChangeActivity::class.java)
        activity.startActivity(intent)
    }

    fun gotoMainCompany(activity: Activity) {
        val intent = Intent(activity, CompanyMainActivity::class.java)
        activity.startActivity(intent)
    }
}