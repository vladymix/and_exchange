package com.altamirano.fabricio.lamanzana.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.altamirano.fabricio.lamanzana.R
import com.altamirano.fabricio.lamanzana.entities.Company

class CompanyAdapter(context: Context, objects: MutableList<Company>) :

    ArrayAdapter<Company>(context, 0, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view: View
        val holder: CompanyHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_company, parent, false)
            holder = CompanyHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as CompanyHolder
        }

        holder.bindView(this.getItem(position))

        return view
    }

    class CompanyHolder(val view: View) {
        private val tvName: TextView = view.findViewById(R.id.c_name)
        private val tvPostalCode: TextView = view.findViewById(R.id.c_postal_code)
        private val tvAdress: TextView = view.findViewById(R.id.c_direcction)

        fun bindView(item: Company?) {
            item?.run {
                tvName.text = name
                tvPostalCode.text = "$postalCode"
                tvAdress.text = direction
            }
        }
    }
}