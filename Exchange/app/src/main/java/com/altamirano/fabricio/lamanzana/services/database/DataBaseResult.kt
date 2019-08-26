package com.altamirano.fabricio.lamanzana.services.database

interface  DataBaseResult<T> {
    fun onResult(t:T?)
}