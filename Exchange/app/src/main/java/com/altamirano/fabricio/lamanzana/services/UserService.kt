package com.altamirano.fabricio.lamanzana.services

import com.altamirano.fabricio.lamanzana.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference


object UserService {

    private var user: FirebaseUser? = null
    private var mAuth: FirebaseAuth? = null

    private lateinit var dataBase: DatabaseReference

    private fun getAuth(): FirebaseAuth {
        if (mAuth == null) {
            mAuth = FirebaseAuth.getInstance()
        }
        return mAuth!!
    }

    fun doLogin(email: String, password: String, listener: IServiceResponse) {

        getAuth().signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if (getUser() != null) {
                    listener.loginSucessFull(getUser()!!)
                } else {
                    listener.serviceError(R.string.login_error_not_fount)
                }
            } else {
                task.exception!!.printStackTrace()
                listener.serviceError(R.string.login_error)
            }
        }
    }

    fun doRegister(email: String, password: String, listener: IServiceResponse) {
        getAuth().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if (getUser() != null) {
                    listener.loginSucessFull(getUser()!!)
                } else {
                    listener.serviceError(R.string.login_error_not_fount)
                }
            } else {
                task.exception!!.printStackTrace()
                listener.serviceError(R.string.login_error)
            }
        }
    }

    fun autoLogin(listener: IServiceResponse) {
        user = getUser()
        user?.let {
            listener.loginSucessFull(it)
        }
    }

    fun getUser(): FirebaseUser? {

        user = getAuth().currentUser
        return user
    }

    fun logout() {
        getAuth().signOut()
        user = null
    }

    fun recoverPassword(email: String, listener: IServiceResponse) {
        getAuth().sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                listener.serviceError(R.string.service_recover)
            } else {
                listener.serviceError(R.string.error_service)
            }
        }
    }

}