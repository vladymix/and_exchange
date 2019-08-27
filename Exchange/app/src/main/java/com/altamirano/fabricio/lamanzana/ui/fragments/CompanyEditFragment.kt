package com.altamirano.fabricio.lamanzana.ui.fragments


import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import com.altamirano.fabricio.lamanzana.R
import com.altamirano.fabricio.lamanzana.app.AppCompany

class CompanyEditFragment : Fragment() {

    private lateinit var tv_code_store:TextView
    private lateinit var tv_name:TextView
    private lateinit var tv_direcction:TextView
    private lateinit var tv_postalcode:TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_company_edit, container, false)

        tv_code_store = view.findViewById(R.id.tv_code_store)
        tv_name = view.findViewById(R.id.tv_name)
        tv_direcction = view.findViewById(R.id.tv_direcction)
        tv_postalcode = view.findViewById(R.id.tv_postalcode)

        tv_code_store.text=  AppCompany.company.code
        tv_name.text=  AppCompany.company.name
        tv_direcction.text=  AppCompany.company.direction
        tv_postalcode.text=  AppCompany.company.postalCode.toString()

        view.findViewById<View>(R.id.btn_save).setOnClickListener { this.onSaveData() }

        return view
    }

    private fun onSaveData() {
        if(tv_name.isEmpty() || tv_direcction.isEmpty() || tv_postalcode.isEmpty()){
            Toast.makeText(this.context,"Todos los datos son necesarios",Toast.LENGTH_LONG).show()
        }else{
            AppCompany.saveCompany(tv_name.text.toString(),tv_direcction.text.toString(),tv_postalcode.text.toString().toInt())
            Toast.makeText(this.context,"Datos guarado correctamente",Toast.LENGTH_LONG).show()
        }
    }

    private fun TextView.isEmpty():Boolean{
        return TextUtils.isEmpty(this.text)
    }


}
