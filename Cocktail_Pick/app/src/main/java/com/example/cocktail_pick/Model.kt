package com.example.cocktail_pick

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Member (
    var id: Int,
    var email: String,
    @SerializedName("username")
    var userName: String,
    @SerializedName("profile_url")
    var profileURL: String,
//    @SerializedName("prefer_recipe_list")
//    var preferRecipeList:
    @SerializedName("my_tag")
    var myTag: List<String>,
): Serializable

