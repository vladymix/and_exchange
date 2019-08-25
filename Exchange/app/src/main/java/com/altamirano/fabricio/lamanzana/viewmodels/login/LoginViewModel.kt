package com.altamirano.fabricio.lamanzana.viewmodels.login

import android.text.TextUtils
import com.altamirano.fabricio.lamanzana.R
import com.altamirano.fabricio.lamanzana.services.IServiceResponse
import com.altamirano.fabricio.lamanzana.services.UserService
import com.google.firebase.auth.FirebaseUser

class LoginViewModel(private val view: ILoginBinding) : ILoginViewModel, IServiceResponse {

    override fun onRecoverPassword(email: String) {
        if (TextUtils.isEmpty(email)) {
            view.showError(R.string.invalid_username)
        }else{
            view.showLoading()
            UserService.recoverPassword(email, this);
        }
    }

    override fun autoLogin() {
        UserService.autoLogin(this)
    }

    override fun loginSucessFull(user: FirebaseUser) {
        this.view.hideLoading()
        this.view.sucessfull()
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
            UserService.doLogin(email, password, this)
        }
    }
}