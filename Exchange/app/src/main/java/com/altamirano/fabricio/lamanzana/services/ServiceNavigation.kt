package com.altamirano.fabricio.lamanzana.services

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.view.inputmethod.InputMethodManager
import com.altamirano.fabricio.lamanzana.NewChangeActivity
import com.altamirano.fabricio.lamanzana.app.AppCompany
import com.altamirano.fabricio.lamanzana.entities.Country
import com.altamirano.fabricio.lamanzana.ui.*


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
    fun gotoSelectCompany(activity: Activity) {
        val intent = Intent(activity, SearchCompanyActivity::class.java)
        activity.startActivity(intent)
    }

    fun logOut(activity: Activity) {
        val intent = Intent(activity, ChooseModeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        activity.startActivity(intent)
        activity.finish()
    }

    fun hideSoftInput(ctx:Activity){
        val inputMethodManager = ctx.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?

        inputMethodManager!!.hideSoftInputFromWindow(ctx.currentFocus?.windowToken, 0)
    }
}