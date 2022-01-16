package com.example.cocktail_pick.Main

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktail_pick.Login.LoginActivity
import com.example.cocktail_pick.MainRepository
import com.example.cocktail_pick.Member
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    private val TAG = "MainViewModel"
    lateinit var currentUserEmail: String
    var currentUser = MutableLiveData<List<Member>>()
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

}