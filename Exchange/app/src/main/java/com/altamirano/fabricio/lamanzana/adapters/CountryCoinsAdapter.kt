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

class CountryCoinsAdapter(context: Context, objects: MutableList<Country>) :
    ArrayAdapter<Country>(context, 0, objects) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view: View
        val holder: CountryCoinsHolder

        if (convertView == null) {
            view = LayoutInflater.from(this.context).inflate(R.layout.item_coin, parent, false)
            holder = CountryCoinsHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as CountryCoinsHolder
        }

        holder.bind(this.getItem(position))


        return view
    }

    class CountryCoinsHolder(view: View) {
        private var image: ImageView
        private var name: TextView
        private var lastUpdate: TextView
        private var exchange: TextView

        init {
            this.image = view.findViewById(R.id.c_image)
            this.name = view.findViewById(R.id.c_name)
            this.lastUpdate = view.findViewById(R.id.c_date)
            this.exchange = view.findViewById(R.id.c_total)
        }

        fun bind(item: Country?) {
            item?.let {
                name.text = it.name
                image.setImageResource(ServiceCountries.getIdImage(image.context, it.code))
                val coin = it.coins?.lastOrNull()

                coin?.let {
                    exchange.text = it.getOnlyChange()
                    lastUpdate.text = it.getAsDatePreview()
                }


            }
        }
    }
}