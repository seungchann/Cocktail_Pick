package com.example.cocktail_pick.Login

import androidx.lifecycle.ViewModel
import com.example.cocktail_pick.MainRepository
import com.example.cocktail_pick.Member

class LoginViewModel constructor(private val repository: MainRepository) : ViewModel() {

    private val TAG = "LoginViewModel"
    lateinit var currentUser: Member
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
