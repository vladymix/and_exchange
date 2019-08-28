package com.altamirano.fabricio.lamanzana.app

import com.altamirano.fabricio.lamanzana.entities.Company
import com.altamirano.fabricio.lamanzana.entities.User
import com.altamirano.fabricio.lamanzana.services.IUserService
import com.altamirano.fabricio.lamanzana.services.database.DataBase
import com.altamirano.fabricio.lamanzana.services.database.DataBaseResult


object AppCustomer {

    lateinit var user:User

    fun loadUser(code:String, listener: IUserService){
        DataBase.searchUser(code, object : DataBaseResult<User> {
            override fun onResult(t: User?) {
                user= t!!
                listener.userResult(user)
            }
        })
    }
}