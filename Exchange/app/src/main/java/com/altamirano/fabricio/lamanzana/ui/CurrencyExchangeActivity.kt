package com.altamirano.fabricio.lamanzana.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.altamirano.fabricio.lamanzana.R
import com.altamirano.fabricio.lamanzana.entities.Coin
import com.altamirano.fabricio.lamanzana.viewmodels.currencyexchange.CurrencyExchangeViewModel
import com.altamirano.fabricio.lamanzana.viewmodels.currencyexchange.ICurrencyExchangeBinding
import com.altamirano.fabricio.lamanzana.viewmodels.currencyexchange.ICurrencyExchangeViewModel
import com.github.mikephil.charting.data.LineData
import kotlinx.android.synthetic.main.activity_customer_content.*
import java.util.*

class CurrencyExchangeActivity : AppCompatActivity(), ICurrencyExchangeBinding {


    lateinit var viewmodel: ICurrencyExchangeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_exchange)

        viewmodel = CurrencyExchangeViewModel(this)
        viewmodel.addListenerExchange()

        tv_total_coin.setOnKeyListener { _, _, _ -> this.onCoinChange() }
        tv_euros.setOnKeyListener { _, _, _ -> this.onEurosChange() }

        tv_total_coin.setOnFocusChangeListener { _, b ->
            if (b) {
                tv_total_coin.setText("")
            }
        }
        tv_euros.setOnFocusChangeListener { _, b ->
            if (b) {
                tv_euros.setText("")
            }
        }
    }

    override fun loadCoins(coins: ArrayList<Coin>) {

    }

    private fun onEurosChange(): Boolean {
        this.viewmodel.onEurosValeChange(tv_euros.text.toString())
        return false
    }

    private fun onCoinChange(): Boolean {
        this.viewmodel.onCoinValeChange(tv_total_coin.text.toString())
        return false

    }

    override fun loadChart(lineData: LineData) {
        lineChar.getAxisRight().setEnabled(false)
        lineChar.data = lineData
        lineChar.invalidate()
    }

    override fun loadLastExchage(coin: Coin?) {
        coin?.let {
            tv_change.text = it.getAsChange()
            tv_change_date.text = it.getAsDatePreview()
            this.viewmodel.onEurosValeChange(tv_euros.text.toString())
        }
    }

    override fun setEurosText(txtEuros: String) {
        tv_euros.setText(txtEuros)
    }

    override fun setCoinValueText(txtCoin: String) {
        tv_total_coin.setText(txtCoin)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewmodel.removeListener()
    }


}
