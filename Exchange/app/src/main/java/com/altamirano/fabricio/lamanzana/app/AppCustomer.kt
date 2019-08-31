package com.altamirano.fabricio.lamanzana.app

import com.altamirano.fabricio.lamanzana.entities.Company
import com.altamirano.fabricio.lamanzana.entities.Country
import com.altamirano.fabricio.lamanzana.entities.User
import com.altamirano.fabricio.lamanzana.services.IUserService
import com.altamirano.fabricio.lamanzana.services.database.DataBase
import com.altamirano.fabricio.lamanzana.services.database.DataBaseResult


object AppCustomer {

    lateinit var user:User
    lateinit var company:Company
    lateinit var country: Country
    var countryPos: Int=0

    fun loadUser(code:String, listener: IUserService){
        DataBase.searchUser(code, object : DataBaseResult<User> {
            override fun onResult(t: User?) {
                user= t!!
                listener.userResult(user)
            }
        })
    }

    fun loadCompanies(listener:DataBaseResult<ArrayList<Company>>){

        DataBase.searchCompanies(object : DataBaseResult<ArrayList<Company>> {
            override fun onResult(t: ArrayList<Company>?) {
                listener.onResult(t)
            }
        })
    }
}