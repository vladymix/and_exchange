package com.altamirano.fabricio.lamanzana.entities

import com.altamirano.fabricio.lamanzana.app.AppCompany

class Coin {
    lateinit var code: String
    lateinit var symbol: String
    lateinit var dateUpdate: String
    var exchange: Double = 0.0

     fun getAsChange():String{
        return "${exchange.asString().replace(".",",")}  $symbol"
     }

    fun getOnlyChange():String{
        return exchange.asString().replace(".",",")
    }

     fun getAsDatePreview():String{
         return AppCompany.getDatePreview(AppCompany.getDateString(dateUpdate)!!)
     }

     fun Double.asString(): String {
         if (this.isInfinite() || this.isNaN()) {
             return "0.0"
         } else {
             try {
                 return String.format("%.2f", this).replace(",",".")
             } catch (e: Exception) {
                 e.printStackTrace()
             }
         }
         return "0.0"
     }
}