package com.altamirano.fabricio.lamanzana.viewmodels.login

interface ILoginBinding {

    fun sucessfull()
    fun showError(resId: Int)
    fun showLoading()
    fun hideLoading()
}