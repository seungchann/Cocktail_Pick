package com.example.cocktail_pick.Login

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.cocktail_pick.MainRepository
import com.example.cocktail_pick.Member
import com.example.cocktail_pick.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

class LoginViewModel constructor(private val repository: MainRepository) : ViewModel() {

    private val TAG = "LoginViewModel"
    lateinit var currentUserEmail: String
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

    fun addMember(member: Member) {
        val response = repository.addUser(member)
        response.enqueue(object : Callback<Member> {
            override fun onResponse(call: Call<Member>, response: Response<Member>) {
                Log.d(TAG, "신규 유저가 등록되었습니다.")
            }

            override fun onFailure(call: Call<Member>, t: Throwable) {
                Log.e(TAG, "유저 등록에 실패했습니다.")
            }
        })
    }

    fun checkUserAccount(context: Context, member: Member) {
        val response = repository.checkUserAccount(member.email)
        this.currentUserEmail = member.email
        response.enqueue(object : Callback<List<Member>> {
            override fun onResponse(call: Call<List<Member>>, response: Response<List<Member>>) {
                Log.d(TAG, "유저가 로드되었습니다.")
                if (response.body()?.size != 0) {
                    (context as LoginActivity).moveToMainActivity()
                } else {
                    addMember(member)
                    (context as LoginActivity).moveToTagFragment()
                }
            }

            override fun onFailure(call: Call<List<Member>>, t: Throwable) {
                Log.e(TAG, "유저 로드에 실패했습니다.")
            }
        })
    }
}
