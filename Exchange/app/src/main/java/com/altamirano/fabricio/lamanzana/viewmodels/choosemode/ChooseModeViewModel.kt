package com.altamirano.fabricio.lamanzana.viewmodels.choosemode

class ChooseModeViewModel(val view: IChooseModeBinding) : IChooseModeViewModel {

    override fun onCompanySelected() {
        view.navigateToLoginCompany();
    }

    override fun onClientSelected() {
       view.navigateToSelectCompany()
    }

}