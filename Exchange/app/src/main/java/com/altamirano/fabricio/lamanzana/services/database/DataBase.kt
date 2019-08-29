package com.altamirano.fabricio.lamanzana.services.database

import com.altamirano.fabricio.lamanzana.app.AppCustomer
import com.altamirano.fabricio.lamanzana.entities.Company
import com.altamirano.fabricio.lamanzana.entities.Country
import com.altamirano.fabricio.lamanzana.entities.User
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

object DataBase {

    private const val DT_COMPANY = "Company"
    private const val DT_USERS = "Users"
    private const val DT_COUNTRIES = "countries"

    private val database = FirebaseDatabase.getInstance()

    private var dbRefCompanies: DatabaseReference = database.reference.child(DT_COMPANY)
    private var dbRefUser: DatabaseReference = database.reference.child(DT_USERS)

    fun searchCompany(user: FirebaseUser, dataBaseResult: DataBaseResult<Company>) {

        val valueEvent: ValueEventListener = object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                dataBaseResult.onResult(null)
            }

            override fun onDataChange(ds: DataSnapshot) {
                val company = ds.child(user.uid).getValue(Company::class.java)
                if (company == null) {
                    dataBaseResult.onResult(createCompany(user))
                } else {
                    dataBaseResult.onResult(company)
                }
            }
        }

        dbRefCompanies.addListenerForSingleValueEvent(valueEvent)
    }

    fun updateCountries(codeCompany: String, countries: ArrayList<Country>) {
        dbRefCompanies.child(codeCompany).child(DT_COUNTRIES).setValue(countries)
    }

    private fun createCompany(user: FirebaseUser): Company {
        val comp = Company(user.uid, user.email!!, "", "", 0, ArrayList())
        dbRefCompanies.child(user.uid).setValue(comp)
        return comp
    }

    fun addCountryListener(company: Company, event: ValueEventListener) {
        dbRefCompanies.child(company.code).child(DT_COUNTRIES).addValueEventListener(event)
    }

    fun removeCountryListener(company: Company, event: ValueEventListener) {
        dbRefCompanies.child(company.code).child(DT_COUNTRIES).removeEventListener(event)
    }

    fun updateCompany(company: Company) {
        dbRefCompanies.child(company.code).setValue(company)
    }


    fun searchUser(code: String, dataBaseResult: DataBaseResult<User>) {

        val valueEvent: ValueEventListener = object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                dataBaseResult.onResult(null)
            }

            override fun onDataChange(ds: DataSnapshot) {
                val usr = ds.child(code).getValue(User::class.java)
                if (usr == null) {
                    dataBaseResult.onResult(createUser(code))
                } else {
                    dataBaseResult.onResult(usr)
                }
            }
        }

        dbRefUser.addListenerForSingleValueEvent(valueEvent)
    }

    fun searchCompanies(event: DataBaseResult<ArrayList<Company>>) {
        val valueEvent: ValueEventListener = object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                event.onResult(ArrayList())
            }

            override fun onDataChange(ds: DataSnapshot) {
                val companies: ArrayList<Company> = ArrayList()
                for (item in ds.children) {
                    val i = item.getValue(Company::class.java)
                    if (i != null) {
                        companies.add(i)
                    }
                }
                event.onResult(companies)
            }
        }

        dbRefCompanies.addListenerForSingleValueEvent(valueEvent)
    }

    private fun createUser(code: String): User {
        val usr = User(code, "")
        dbRefUser.child(code).setValue(usr)
        return usr
    }


}