package com.altamirano.fabricio.lamanzana.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
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

    fun TextView.setTextChange(t: (s:CharSequence) -> Unit){

        this.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                t.invoke(p0!!)
            }
        })
    }

    override fun loadCountries(allCountries: ArrayList<Country>) {
        adapter.addItems(allCountries)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)
        viewModel = AllCountriesViewModel(this)
        adapter = CountriesAdapter(this, ArrayList())
        listview.adapter = adapter
        listview.emptyView = emtpyView
        listview.setOnItemClickListener { _, _, pos, _ ->  this.onItemSelected(adapter.getItem(pos))}
        viewModel.loadCountries()

        et_filter.setTextChange{ t -> this.textChange(t) }
    }

    private fun textChange(p0: CharSequence?){
        adapter.filter.filter(p0)
    }

    private fun onItemSelected(item: Country?) {
        item?.let {
            ServiceNavigation.goToCreateCoin(this, item)
        }
        finish()
    }
}
