package com.eneskoc.kotlin_food_list.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    @JvmStatic
    val client: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("https://s3-eu-west-1.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @JvmStatic
    fun createClientInterface(): ProductAPI {
        return client.create(ProductAPI::class.java)
    }
}