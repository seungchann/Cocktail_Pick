package com.example.cocktail_pick

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User (
    var email: String,
    @SerializedName("username")
    var userName: String,
    @SerializedName("profile_url")
    var profileURL: String,
//    @SerializedName("prefer_recipe_list")
//    var preferRecipeList:
//    @SerializedName("my_tag")
    var myTag: List<String>,
): Serializable

data class Member (
    @SerializedName("username")
    var userName: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("profile_url")
    var profileURL: String,
): Serializable

data class Tag (
    var color: String,
    var taste: String,
): Serializable

