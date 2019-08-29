package com.altamirano.fabricio.lamanzana.entities

import com.altamirano.fabricio.lamanzana.app.AppCompany

class Coin {
    lateinit var code: String
    lateinit var dateUpdate: String
    var exchange: Double = 0.0

     fun getAsChange():String{
        return "${exchange.asString()}  $code"
     }

     fun getAsDatePreview():String{
         return AppCompany.getDatePreview(AppCompany.getDateString(dateUpdate)!!)
     }

     fun Double.asString(): String {
         if (this.isInfinite() || this.isNaN()) {
             return "0.0"
         } else {
             try {
                 return String.format("%.3f", this).replace(",",".")
             } catch (e: Exception) {
                 e.printStackTrace()
             }
         }
         return "0.0"
     }
}