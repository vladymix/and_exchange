package com.altamirano.fabricio.lamanzana.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.altamirano.fabricio.lamanzana.R
import com.altamirano.fabricio.lamanzana.services.ServiceNavigation
import com.altamirano.fabricio.lamanzana.viewmodels.choosemode.ChooseModeViewModel
import com.altamirano.fabricio.lamanzana.viewmodels.choosemode.IChooseModeBinding
import com.altamirano.fabricio.lamanzana.viewmodels.choosemode.IChooseModeViewModel
import kotlinx.android.synthetic.main.activity_choose_mode.*

class ChooseModeActivity : AppCompatActivity(), IChooseModeBinding {

    private lateinit var viewmodel:IChooseModeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_mode)
        viewmodel = ChooseModeViewModel(this)

        btn_client.setOnClickListener { viewmodel.onClientSelected() }
        btn_company.setOnClickListener { viewmodel.onCompanySelected() }
    }

    override fun navigateToLoginCompany() {
        ServiceNavigation.gotoLogin(this)
    }

    override fun navigateToSelectCompany() {
        Toast.makeText(this,"Select company",Toast.LENGTH_LONG).show()
    }
}
