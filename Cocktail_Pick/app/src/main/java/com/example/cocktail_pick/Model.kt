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

data class Recipe (
    @SerializedName("user_id")
    var userId: Int,
    var intro: String,
    var alcohol: Int,
    @SerializedName("cocktail_name")
    var cocktailName: String,
    var glass: String,
    var ice: Int,
    @SerializedName("garnish_first")
    var garnishFirst: String,
    @SerializedName("garnish_second")
    var garnishSecond: String,
    var base: Int,
    var juice: Int,
    var liqueur: Int,
    var etc: Int,
    var posting: String,
):Serializable

data class Base (
    var name: String,
    var onz: Float,
    var company: String,
)

data class Juice (
    var name: String,
    var onz: Float,
)

data class Liqueur (
    var name: String,
    var onz: Float,
    var company: String,
)

data class Etc (
    var name: String,
    var onz: Float,
)


