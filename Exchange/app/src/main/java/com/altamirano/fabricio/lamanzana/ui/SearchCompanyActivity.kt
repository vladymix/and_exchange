package com.altamirano.fabricio.lamanzana.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.altamirano.fabricio.lamanzana.R
import com.altamirano.fabricio.lamanzana.adapters.CompanyAdapter
import com.altamirano.fabricio.lamanzana.app.AppCustomer
import com.altamirano.fabricio.lamanzana.entities.Company
import com.altamirano.fabricio.lamanzana.services.ServiceNavigation
import com.altamirano.fabricio.lamanzana.services.database.DataBaseResult
import kotlinx.android.synthetic.main.activity_search_company.*

class SearchCompanyActivity : AppCompatActivity(), DataBaseResult<ArrayList<Company>> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_company)
        listview.emptyView = ly_empty

        AppCustomer.loadCompanies(this)

        listview.setOnItemClickListener { adapterView, _, pos, _ ->this.onItemSelected(adapterView.getItemAtPosition(pos) as Company)  }
    }

    private fun onItemSelected(company: Company) {
        ServiceNavigation.gotoSelectCountry(this, company)

    }

    override fun onResult(t: ArrayList<Company>?) {
        listview.adapter = CompanyAdapter(this, t!!)
    }
}
