package com.altamirano.fabricio.lamanzana.viewmodels.login

import android.text.TextUtils
import com.altamirano.fabricio.lamanzana.R

class LoginViewModel(private val view: ILoginBinding) : ILoginViewModel {


    override fun onLogin(email: String, password: String) {

        if (TextUtils.isEmpty(email)) {
            view.showError(R.string.invalid_username)
        } else if (TextUtils.isEmpty(password) || password.length < 6) {
            view.showError(R.string.invalid_password)
        }else{
            view.showLoagin()
           // view.showError(R.string.app_name)
            //view.hideLoading()
            // view.sucessfull()
        }
    }
}