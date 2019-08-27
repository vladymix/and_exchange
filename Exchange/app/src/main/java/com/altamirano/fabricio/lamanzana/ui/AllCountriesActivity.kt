package com.altamirano.fabricio.lamanzana.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.altamirano.fabricio.lamanzana.R
import com.altamirano.fabricio.lamanzana.adapters.CountriesAdapter
import com.altamirano.fabricio.lamanzana.entities.Country
import com.altamirano.fabricio.lamanzana.services.ServiceNavigation
import com.altamirano.fabricio.lamanzana.viewmodels.allcountries.AllCountriesViewModel
import com.altamirano.fabricio.lamanzana.viewmodels.allcountries.IAllCountriesBinding
import com.altamirano.fabricio.lamanzana.viewmodels.allcountries.IAllCountriesViewModel
import kotlinx.android.synthetic.main.activity_countries.*

class AllCountriesActivity : AppCompatActivity(), IAllCountriesBinding {

    private lateinit var viewModel: IAllCountriesViewModel
    private lateinit var adapter: CountriesAdapter
    private var listCountries: ArrayList<Country> = ArrayList()

    override fun loadCountries(allCountries: ArrayList<Country>) {
        listCountries.clear()
        listCountries.addAll(allCountries)
        adapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)
        viewModel = AllCountriesViewModel(this)
        adapter = CountriesAdapter(this, listCountries)
        listview.adapter = adapter
        listview.emptyView = emtpyView
        listview.setOnItemClickListener { _, _, pos, _ ->  this.onItemSelected(adapter.getItem(pos))}
        viewModel.loadCountries()
    }

    private fun onItemSelected(item: Country?) {
        item?.let {
            ServiceNavigation.goToCreateCoin(this, item)
        }
        finish()
    }
}
