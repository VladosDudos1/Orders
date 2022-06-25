package com.example.pizzaorders

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface Api {
    companion object {

        fun createApi(): Api {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val retrofit = Retrofit.Builder()
                .baseUrl("")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(Api::class.java)
        }
    }
}