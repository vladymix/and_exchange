package com.altamirano.fabricio.lamanzana.viewmodels.login

import android.text.TextUtils
import com.altamirano.fabricio.lamanzana.R
import com.altamirano.fabricio.lamanzana.entities.Company
import com.altamirano.fabricio.lamanzana.app.AppCompany
import com.altamirano.fabricio.lamanzana.services.IServiceResponse
import com.altamirano.fabricio.lamanzana.services.ServiceUser
import com.google.firebase.auth.FirebaseUser

class LoginViewModel(private val view: ILoginBinding) : ILoginViewModel, IServiceResponse {

    override fun companyResult(comp: Company) {
        this.view.hideLoading()
        this.view.sucessfull()
    }

    override fun onRecoverPassword(email: String) {
        if (TextUtils.isEmpty(email)) {
            view.showError(R.string.invalid_username)
        }else{
            view.showLoading()
            ServiceUser.recoverPassword(email, this);
        }
    }

    override fun autoLogin() {
        ServiceUser.autoLogin(this)
    }

    override fun loginSucessFull(user: FirebaseUser) {
        this.view.showLoading()
        AppCompany.loadCompany(user,this)
    }

    override fun serviceError(idError: Int) {
        this.view.hideLoading()
        this.view.showError(idError)
    }

    override fun onLogin(email: String, password: String) {
        if (TextUtils.isEmpty(email)) {
            view.showError(R.string.invalid_username)
        } else if (TextUtils.isEmpty(password) || password.length < 6) {
            view.showError(R.string.invalid_password)
        } else {
            view.showLoading()
            ServiceUser.doLogin(email, password, this)
        }
    }
}