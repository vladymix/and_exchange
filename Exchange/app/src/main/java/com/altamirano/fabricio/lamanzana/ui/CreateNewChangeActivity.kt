package com.altamirano.fabricio.lamanzana.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.altamirano.fabricio.lamanzana.R
import com.altamirano.fabricio.lamanzana.adapters.CountriesAdapter
import com.altamirano.fabricio.lamanzana.app.AppCompany
import kotlinx.android.synthetic.main.activity_new_change.*
import java.util.*

class CreateNewChangeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_change)
        btnOnCreateCoin.setOnClickListener { this.onCreateCoin() }
        btnDelete.setOnClickListener { this.onDeleteCoin() }
        CountriesAdapter.CountryHolder(ly_country).bindView(AppCompany.countrySelected)

        tv_code_cuntry.setText(AppCompany.countrySelected.code)
        tv_code_coin.setText(AppCompany.countrySelected.codeCoin)
        tv_code_symbol.setText(AppCompany.countrySelected.symbol)
    }

    private fun onDeleteCoin() {
        AppCompany.deleteCoin(AppCompany.countrySelected)
        finish()
    }

    private fun onCreateCoin() {
        AppCompany.addCoin(
            AppCompany.countrySelected,
            tv_code_coin.text.toString(),
            tv_code_symbol.text.toString(),
            Date(),
            tv_change.text.toString().toDouble()
        )

        Toast.makeText(this, "Cambio añadido", Toast.LENGTH_LONG).show()
        finish()
    }
}
