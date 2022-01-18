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

    var tag: ArrayList<Int> = ArrayList<Int>(),
): Serializable

data class Tag (
    var id: Int,
    var color: String,
    var taste: String,
): Serializable

data class Recipe (
    @SerializedName("user_id")
    var userId: Int,
    var intro: String,
    @SerializedName("cocktail_name")
    var cocktailName: String,
    var glass: String,
    var ice: Int,
    @SerializedName("garnish_first")
    var garnishFirst: String,
    @SerializedName("garnish_second")
    var garnishSecond: String,
    @SerializedName("cocktail_color")
    var cocktailColor: String,
    var posting: String,
    var tags: List<Int>,

    @SerializedName("base_name")
    var baseName: String,
    @SerializedName("base_oz")
    var baseOz: Float,
    @SerializedName("juice_name")
    var juiceName: String,
    @SerializedName("juice_oz")
    var juiceOz: Float,
    @SerializedName("liqueur_name")
    var liqueurName: String,
    @SerializedName("liqueur_oz")
    var liqueurOz: Float,
    @SerializedName("etc_name")
    var etcName: String,
    @SerializedName("etc_oz")
    var etcOz: Float,

    var step: String,
):Serializable

data class Product (
    var companyName: String,
    var base: String,
    var picture: Int,
):Serializable


