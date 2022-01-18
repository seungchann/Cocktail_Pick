package com.example.cocktail_pick

class MainRepository constructor(private val retrofitService: RetrofitService) {

    /*
    fun getAllData() = retrofitService.getAllData()
     */

    fun addUser(member: Member) = retrofitService.addUser(member)
    fun checkUserAccount(email: String) = retrofitService.checkUserAccount(email)
    fun loadTagData() = retrofitService.loadTagData()
    fun loadTagBasedRecipe() = retrofitService.loadTagBasedRecipe()
    fun loadBaseBasedRecipe(base: String) = retrofitService.loadBaseBasedRecipe(base)

}