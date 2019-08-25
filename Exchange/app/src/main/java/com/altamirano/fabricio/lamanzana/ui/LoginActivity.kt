package com.altamirano.fabricio.lamanzana.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.altamirano.fabricio.lamanzana.R
import com.altamirano.fabricio.lamanzana.services.ServiceNavigation
import com.altamirano.fabricio.lamanzana.viewmodels.login.ILoginBinding
import com.altamirano.fabricio.lamanzana.viewmodels.login.ILoginViewModel
import com.altamirano.fabricio.lamanzana.viewmodels.login.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), ILoginBinding {

    private lateinit var viewModel: ILoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = LoginViewModel(this)

        btn_login.setOnClickListener { this.viewModel.onLogin(tv_email.viewString(), tv_password.viewString()) }
        tv_recoverpassword.setOnClickListener { this.viewModel.onRecoverPassword(tv_email.viewString()) }

        viewModel.autoLogin();
    }

    override fun sucessfull() {
        Toast.makeText(this, "Wellcome", Toast.LENGTH_LONG).show()
        ServiceNavigation.gotoMainCompany(this)
    }

    override fun showError(resId: Int) {
        Toast.makeText(this, resId, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        ly_progress.visibility = View.VISIBLE

    }

    override fun hideLoading() {
        ly_progress.visibility = View.GONE
    }

    private fun TextView.viewString(): String {
        return this.text.toString()
    }
}
