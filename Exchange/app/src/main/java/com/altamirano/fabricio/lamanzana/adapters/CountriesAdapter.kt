package com.altamirano.fabricio.lamanzana.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.altamirano.fabricio.lamanzana.R
import com.altamirano.fabricio.lamanzana.entities.Country
import com.altamirano.fabricio.lamanzana.services.ServiceCountries

class CountriesAdapter(context: Context, objects: MutableList<Country>) :
    ArrayAdapter<Country>(context, 0, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view: View
        val holder: CountryHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_country, parent, false)
            holder = CountryHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as CountryHolder
        }

        holder.bindView(this.getItem(position))

        return view
    }

     class CountryHolder(val view: View) {

        val image: ImageView
        val text: TextView

        init {
            image = view.findViewById(R.id.c_image)
            text = view.findViewById(R.id.c_name)
        }

        fun bindView(item: Country?) {
            item?.run {
                text.text = item.name
                image.setImageResource(ServiceCountries.getIdImage(image.context, item.code))
            }
        }

    }


}