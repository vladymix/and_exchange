package com.altamirano.fabricio.lamanzana.viewmodels.login

interface ILoginViewModel {
    fun onLogin(email: String, password: String)
    fun autoLogin()
    fun onRecoverPassword(email: String)
}
