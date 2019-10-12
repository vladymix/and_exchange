package com.altamirano.fabricio.lamanzana.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.provider.Settings.Secure
import androidx.appcompat.app.AppCompatActivity
import com.altamirano.fabricio.lamanzana.R
import com.altamirano.fabricio.lamanzana.services.ServiceNavigation
import com.altamirano.fabricio.lamanzana.viewmodels.choosemode.ChooseModeViewModel
import com.altamirano.fabricio.lamanzana.viewmodels.choosemode.IChooseModeBinding
import com.altamirano.fabricio.lamanzana.viewmodels.choosemode.IChooseModeViewModel
import com.altamirano.fabricio.lamanzana.widgets.ProgressDialog
import kotlinx.android.synthetic.main.activity_choose_mode.*


class ChooseModeActivity : AppCompatActivity(), IChooseModeBinding {

    private var dialog: ProgressDialog? = null
    private lateinit var viewmodel: IChooseModeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_mode)
        viewmodel = ChooseModeViewModel(this)

        btn_client.setOnClickListener { viewmodel.onClientSelected(getUserId()) }
        btn_company.setOnClickListener { viewmodel.onCompanySelected() }
    }

    override fun navigateToLoginCompany() {
        ServiceNavigation.gotoLogin(this)
    }

    override fun navigateToSelectCompany() {
        ServiceNavigation.gotoSelectCompany(this)
    }

    @SuppressLint("HardwareIds")
    fun getUserId(): String {
        val android_id = Secure.getString(
            this.getContentResolver(),
            Secure.ANDROID_ID
        )

        return "${Build.MANUFACTURER}-${Build.MODEL}-${android_id}"
    }

    override fun showProgress() {
        dialog = ProgressDialog(this)
        dialog?.run {
            setMessage(R.string.lb_loading_office)
            show()
        }
    }

    override fun dissmisProgress() {
        dialog?.dismiss()
    }

}
