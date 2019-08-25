package com.altamirano.fabricio.lamanzana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.altamirano.fabricio.lamanzana.adapters.CountriesAdapter
import com.altamirano.fabricio.lamanzana.services.ExchangeApp
import com.altamirano.fabricio.lamanzana.services.ServiceNavigation
import kotlinx.android.synthetic.main.activity_new_change.*
import java.util.*

class NewChangeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_change)
        btnOnCreateCoin.setOnClickListener { this.onCreateCoin() }

        CountriesAdapter.CountryHolder(ly_country).bindView(ServiceNavigation.countrySeleccted)
        tv_code_coin.setText(ServiceNavigation.countrySeleccted.code)
    }

    private fun onCreateCoin() {

        ExchangeApp.addCoin(
            ServiceNavigation.countrySeleccted,
            tv_code_coin.text.toString(),
            Date(),
            tv_change.text.toString().toDouble()
        )

        Toast.makeText(this,"Cambio añadido",Toast.LENGTH_LONG).show()
    }
}
