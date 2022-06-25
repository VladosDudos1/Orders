package com.example.pizzaorders

import android.content.Context

class DataManager(private val baseContext: Context) {
    val api = Api.createApi()
}