package com.altamirano.fabricio.lamanzana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.altamirano.fabricio.lamanzana.adapters.CountriesAdapter
import com.altamirano.fabricio.lamanzana.app.AppCompany
import com.altamirano.fabricio.lamanzana.services.ServiceNavigation
import kotlinx.android.synthetic.main.activity_new_change.*
import java.util.*

class NewChangeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_change)
        btnOnCreateCoin.setOnClickListener { this.onCreateCoin() }

        CountriesAdapter.CountryHolder(ly_country).bindView(AppCompany.countrySelected)
        tv_code_coin.setText(AppCompany.countrySelected.code)
    }

    private fun onCreateCoin() {

        AppCompany.addCoin(
            AppCompany.countrySelected,
            tv_code_coin.text.toString(),
            Date(),
            tv_change.text.toString().toDouble()
        )

        Toast.makeText(this,"Cambio añadido",Toast.LENGTH_LONG).show()
        finish()
    }
}
