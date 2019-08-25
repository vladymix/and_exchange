package com.altamirano.fabricio.lamanzana.services

import com.google.firebase.auth.FirebaseUser

interface IServiceResponse{
    fun loginSucessFull(user:FirebaseUser)
    fun serviceError(idError:Int)
}