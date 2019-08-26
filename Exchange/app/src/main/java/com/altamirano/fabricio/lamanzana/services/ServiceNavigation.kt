package com.altamirano.fabricio.lamanzana.services

import android.app.Activity
import android.content.Intent
import com.altamirano.fabricio.lamanzana.ui.AllCountriesActivity
import com.altamirano.fabricio.lamanzana.NewChangeActivity
import com.altamirano.fabricio.lamanzana.app.AppCompany
import com.altamirano.fabricio.lamanzana.entities.Country
import com.altamirano.fabricio.lamanzana.ui.ChooseModeActivity
import com.altamirano.fabricio.lamanzana.ui.CompanyMainActivity
import com.altamirano.fabricio.lamanzana.ui.LoginActivity

object ServiceNavigation {


    fun goToSearchCountry(activity: Activity) {
        val intent = Intent(activity, AllCountriesActivity::class.java)
        activity.startActivity(intent)
    }

    fun gotoLogin(activity: Activity) {
        val intent = Intent(activity, LoginActivity::class.java)
        activity.startActivity(intent)
    }

    fun goToCreateCoin(activity: Activity,country: Country) {
        AppCompany.countrySelected = country
        val intent = Intent(activity, NewChangeActivity::class.java)
        activity.startActivity(intent)
    }

    fun gotoMainCompany(activity: Activity) {
        val intent = Intent(activity, CompanyMainActivity::class.java)
        activity.startActivity(intent)
    }

    fun logOut(activity: Activity) {
        val intent = Intent(activity, ChooseModeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        activity.startActivity(intent)
        activity.finish()
    }
}