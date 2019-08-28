package com.altamirano.fabricio.lamanzana.adapters

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.altamirano.fabricio.lamanzana.R
import com.altamirano.fabricio.lamanzana.entities.Country
import com.altamirano.fabricio.lamanzana.services.ServiceCountries

@Suppress("UNCHECKED_CAST")
class CountriesAdapter(context: Context, objects: MutableList<Country>) : BaseAdapter(), Filterable {

    private var list: ArrayList<Country> = ArrayList()
    private var display: ArrayList<Country> = ArrayList()
    private var context: Context

    init {
        list.clear()
        display.clear()

        list.addAll(objects)
        display.addAll(objects)
        this.context = context
    }

    override fun getItem(p0: Int): Country {
        return display[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return display.size
    }

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

        private val image: ImageView = view.findViewById(R.id.c_image)
        private val text: TextView = view.findViewById(R.id.c_name)

        fun bindView(item: Country?) {
            item?.run {
                text.text = item.name
                image.setImageResource(ServiceCountries.getIdImage(image.context, item.code))
            }
        }

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                synchronized(this) {

                    val results = FilterResults()

                    if (TextUtils.isEmpty(p0)) {
                        results.values = list
                        results.count = list.size
                    } else {
                        val filte = list.filter { c -> c.toString().contains(p0.toString()) }
                        results.values = filte
                        results.count = filte.size
                    }
                    return results
                }


            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                display = p1!!.values as ArrayList<Country>
                notifyDataSetChanged()
            }

        }
    }

    fun addItems(items: ArrayList<Country>) {
        this.list.clear()
        this.display.clear()
        this.list.addAll(items)
        this.display.addAll(items)
        this.notifyDataSetChanged()
    }
}