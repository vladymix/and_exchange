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

        holder.bind(this.getItem(position), position)


        return view
    }

    class CountryCoinsHolder(view: View) {
        private var image: ImageView = view.findViewById(R.id.iv_flag)
        private var ivbackground: ImageView = view.findViewById(R.id.iv_background)
        private var name: TextView = view.findViewById(R.id.tv_name)
        private var lastUpdate: TextView = view.findViewById(R.id.tv_lastupdate)
        private var exchange: TextView = view.findViewById(R.id.tv_change)

        fun bind(item: Country?, position: Int) {
            item?.let {

                when (position % 3) {
                    0 -> this.ivbackground.setImageResource(R.drawable.ic_coin_1)
                    1 -> this.ivbackground.setImageResource(R.drawable.ic_coin_2)
                    2 -> this.ivbackground.setImageResource(R.drawable.ic_coin_3)
                }

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