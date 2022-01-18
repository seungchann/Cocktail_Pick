package com.example.cocktail_pick.Main

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktail_pick.*
import com.example.cocktail_pick.Login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    private val TAG = "MainViewModel"
    private val BASE = "BASE BASED"
    lateinit var currentUserEmail: String
    lateinit var base: String
    var currentUser = MutableLiveData<List<Member>>()
    var tagBasedRecipeList = MutableLiveData<List<RecipeReceive>>()
    var baseBasedRecipeList = MutableLiveData<List<RecipeReceive>>()
    var productList = mutableListOf<Product>()
    /*
    val dataList = MutableLiveData<List<Int>>()


    fun getAllData() {
        val response = repository.getAllData()
        response.enqueue(object : Callback<List<Int>> {
            override fun onResponse(call: Call<List<Int>>, response: Response<List<Int>>) {
                dataList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Int>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
     */

    fun loadUserAccount() {
        val response = repository.checkUserAccount(currentUserEmail)
        response.enqueue(object : Callback<List<Member>> {
            override fun onResponse(call: Call<List<Member>>, response: Response<List<Member>>) {
                Log.d(TAG, "유저가 로드되었습니다.")
                currentUser.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Member>>, t: Throwable) {
                Log.e(TAG, "유저 로드에 실패했습니다.")
            }
        })
    }

    fun loadTagBasedRecipe() {
        val response = repository.loadTagBasedRecipe()
        response.enqueue(object : Callback<List<RecipeReceive>> {
            override fun onResponse(
                call: Call<List<RecipeReceive>>,
                response: Response<List<RecipeReceive>>
            ) {
                Log.d(TAG, "레시피가 로드되었습니다.")
                tagBasedRecipeList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<RecipeReceive>>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }

    fun loadBaseBasedRecipe(base: String) {
        val response = repository.loadBaseBasedRecipe(base)
        this.base = base
        response.enqueue(object : Callback<List<RecipeReceive>> {
            override fun onResponse(
                call: Call<List<RecipeReceive>>,
                response: Response<List<RecipeReceive>>
            ) {
                Log.d(BASE, "베이스 기반 레시피가 로드되었습니다.")
                baseBasedRecipeList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<RecipeReceive>>, t: Throwable) {
                Log.e(BASE, t.message.toString())
            }
        })
    }

    fun initProductList() {
            productList.add(Product("잭 다니엘", "Whiskey", R.drawable.jack_danial))
            productList.add(Product("메이커스 마크", "Whiskey", R.drawable.makersmark))
            productList.add(Product("앱솔루트", "Vodka", R.drawable.absolute))
            productList.add(Product("호셀 쿠엘보", "Tequila", R.drawable.josecuervo))
            productList.add(Product("참이슬","Soju",R.drawable.cham))
            productList.add(Product("봄베이 사파이어","Gin",R.drawable.bombay))
            productList.add(Product("바카디", "Rum",R.drawable.bacardi))
    }



}