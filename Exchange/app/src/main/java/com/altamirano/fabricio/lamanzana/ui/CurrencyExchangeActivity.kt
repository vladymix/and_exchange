package com.altamirano.fabricio.lamanzana.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.altamirano.fabricio.lamanzana.R
import com.altamirano.fabricio.lamanzana.app.AppCustomer
import com.altamirano.fabricio.lamanzana.entities.Coin
import com.altamirano.fabricio.lamanzana.viewmodels.currencyexchange.CurrencyExchangeViewModel
import com.altamirano.fabricio.lamanzana.viewmodels.currencyexchange.ICurrencyExchangeBinding
import com.altamirano.fabricio.lamanzana.viewmodels.currencyexchange.ICurrencyExchangeViewModel

class CurrencyExchangeActivity : AppCompatActivity(), ICurrencyExchangeBinding {

    lateinit var viewmodel: ICurrencyExchangeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_exchange)

        viewmodel = CurrencyExchangeViewModel(this)
        viewmodel.addListenerExchange(AppCustomer.company.countries[0])

      /*  fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
    }

    override fun loadExchange(arrayList: ArrayList<Coin>?) {
        if(arrayList!=null){

        }
    }


}
