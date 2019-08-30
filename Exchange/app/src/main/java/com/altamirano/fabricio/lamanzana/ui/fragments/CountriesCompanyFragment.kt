package com.altamirano.fabricio.lamanzana.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.altamirano.fabricio.lamanzana.R
import com.altamirano.fabricio.lamanzana.adapters.CountryCoinsAdapter
import com.altamirano.fabricio.lamanzana.app.AppCompany
import com.altamirano.fabricio.lamanzana.entities.Country
import com.altamirano.fabricio.lamanzana.services.ServiceNavigation
import com.altamirano.fabricio.lamanzana.viewmodels.countriescompany.CountriesCompanyViewModel
import com.altamirano.fabricio.lamanzana.viewmodels.countriescompany.ICountriesCompanyBinding
import com.altamirano.fabricio.lamanzana.viewmodels.countriescompany.ICountriesCompanyViewModel

class CountriesCompanyFragment : Fragment(), ICountriesCompanyBinding {

    private lateinit var listview: ListView
    private lateinit var viewModel: ICountriesCompanyViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_countries_company, container, false)

        listview = view.findViewById(R.id.listview)
        listview.emptyView = view.findViewById(R.id.ly_empty)

        listview.adapter =  CountryCoinsAdapter(this.context!!,AppCompany.company.countries)

        viewModel = CountriesCompanyViewModel(this)
        viewModel.startListener()

        view.findViewById<View>(R.id.btn_addCountry).setOnClickListener { this.onAddCountry() }

        listview.setOnItemClickListener { _, _, pos, _ -> this.onItemSeleccted(listview.adapter.getItem(pos) as Country ) }

        return view;
    }

    private fun onItemSeleccted(country: Country) {
        ServiceNavigation.goToCreateCoin(this.context!!,country)
    }

    private fun onAddCountry() {
        ServiceNavigation.goToSearchCountry(activity!!)
    }

    override fun loadCountries(items: ArrayList<Country>) {
        (listview.adapter as CountryCoinsAdapter).notifyDataSetChanged()
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.removeListener()
    }
}
