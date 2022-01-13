package com.example.cocktail_pick

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {


    /*
    @GET("/{test}")
    fun getAllData(): Call<List<Int>>
     */

    companion object {
        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {

            if(retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("URL")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}