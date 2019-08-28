package com.altamirano.fabricio.lamanzana.viewmodels.choosemode

import android.util.Log
import com.altamirano.fabricio.lamanzana.app.AppCustomer
import com.altamirano.fabricio.lamanzana.entities.User
import com.altamirano.fabricio.lamanzana.services.IUserService

class ChooseModeViewModel(val view: IChooseModeBinding) : IChooseModeViewModel, IUserService {

    override fun userResult(user: User) {
        Log.i("user",user.toString())
        view.navigateToSelectCompany()
    }


    override fun onCompanySelected() {
        view.navigateToLoginCompany();
    }

    override fun onClientSelected(uid:String) {
        AppCustomer.loadUser(uid,this)
    }

}