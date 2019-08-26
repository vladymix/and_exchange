package com.altamirano.fabricio.lamanzana.services


import com.altamirano.fabricio.lamanzana.entities.Company
import com.google.firebase.auth.FirebaseUser

interface IServiceResponse{

    fun loginSucessFull(user:FirebaseUser)
    fun serviceError(idError:Int)
    fun companyResult(comp: Company)
}