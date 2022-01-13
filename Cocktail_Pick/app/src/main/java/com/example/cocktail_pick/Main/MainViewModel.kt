package com.example.cocktail_pick.Main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    private val TAG = "MainViewModel"
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

}