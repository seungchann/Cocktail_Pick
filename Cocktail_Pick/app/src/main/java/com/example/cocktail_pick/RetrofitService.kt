package com.example.cocktail_pick

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {


/*
    @GET("/juice/")
    fun getAllJuice(): Call<List<Int>>

 */

    @POST("/user/post/")
    fun addUser(@Body member: Member): Call<Member>

    @GET("/user/post/")
    fun checkUserAccount(@Query("search") email: String): Call<List<Member>>

    @GET("/tag/")
    fun loadTagData(): Call<List<Tag>>

    @GET("/recipe/tag/")
    fun loadTagBasedRecipe(): Call<List<RecipeReceive>>

    @POST("/recipe/post/")
    fun addRecipe(@Body recipe: Recipe): Call<Recipe>


    companion object {
        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {

            if(retrofitService == null) {
                val interceptor = HttpLoggingInterceptor()
                if (BuildConfig.DEBUG) {
                    interceptor.level = HttpLoggingInterceptor.Level.BODY
                } else {
                    interceptor.level = HttpLoggingInterceptor.Level.NONE
                }

                val client = OkHttpClient().newBuilder()
                    .addNetworkInterceptor(interceptor)
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl("http://192.249.18.191:80")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}