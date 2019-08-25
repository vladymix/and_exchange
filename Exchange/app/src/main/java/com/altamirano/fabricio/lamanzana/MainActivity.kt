package com.altamirano.fabricio.lamanzana

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.altamirano.fabricio.lamanzana.entities.Exchange
import com.altamirano.fabricio.lamanzana.services.ExchangeApp
import com.altamirano.fabricio.lamanzana.services.ServiceNavigation
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ValueEventListener {

    private lateinit var myRef: DatabaseReference

    private var coinExchange: Double = 0.0

    override fun onCancelled(p0: DatabaseError) {
        ly_progress.visibility = View.GONE
    }

    override fun onDataChange(ds: DataSnapshot) {
        val dok = ds.child("Exchange").getValue(Exchange::class.java)
        dok?.run {
            ExchangeApp.exchange = dok
            try{
                loadExchanges("ECU")
            }catch (ex:Exception){
                ex.printStackTrace()
            }

        }
        ly_progress.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ly_progress.visibility = View.VISIBLE
        ExchangeApp.exchange = Exchange(ArrayList())

        val database = FirebaseDatabase.getInstance()
        myRef = database.getReference()
        myRef.addValueEventListener(this)

        btnCreateCoin.setOnClickListener { this.onCreateCoin() }

        tv_coin.setOnKeyListener { view, i, keyEvent -> this.onCoinChange(tv_coin.text.toString()) }
        tv_euros.setOnKeyListener { view, i, keyEvent -> this.onEurosChange(tv_euros.text.toString()) }

    }

    private fun onCoinChange(textCoin: String): Boolean {
        try {
            val numCoin = textCoin.toDouble() / coinExchange
            tv_euros.setText(numCoin.asString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false

    }

    private fun onEurosChange(textEuros: String): Boolean {
        try {
            val numCoin = textEuros.toDouble() * coinExchange
            tv_coin.setText(numCoin.asString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    private fun loadExchanges(codeCountry:String) {
        val entries = ArrayList<Entry>()
        var counter = 1f
        for (coin in ExchangeApp.getCountry(codeCountry)?.coins!!) {
            counter += 1f
            entries.add(Entry(counter, coin.exchange.toFloat()))
        }

        val dataset = LineDataSet(entries, ExchangeApp.getCountry("ECU")?.code)
        dataset.fillColor = Color.BLUE
        dataset.setDrawFilled(true)

        dataset.color = Color.BLUE
        dataset.lineWidth = 2f

        dataset.valueTextSize = 10f

        dataset.circleRadius = 5f
        dataset.setCircleColor(Color.BLUE)

        val lineData = LineData(dataset)
        lineChar.data = lineData
        lineChar
        lineChar.invalidate()

        lineChar.getAxisRight().setEnabled(false)

        val coin = ExchangeApp.getLastExchange(codeCountry, "USD");
        coin?.run {
            coinExchange = coin.exchange
            tv_target_coin.setText("$coinExchange ${coin.code}")

            try {
                val numCoin = tv_euros.text.toString().toDouble() * coinExchange
                tv_coin.setText(numCoin.asString())
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }

    private fun onCreateCoin() {
        ServiceNavigation.goToSearchCountry(this)
    }
}

fun Double.asString(): String {
    if (this.isInfinite() || this.isNaN()) {
        return "0.0"
    } else {
        try {
            return String.format("%.2f", this).replace(",",".")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return "0.0"
}
