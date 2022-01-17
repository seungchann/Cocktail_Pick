package com.example.cocktail_pick

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserReceive (
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

data class TagReceive (
    var color: String,
    var taste: String,
): Serializable

data class RecipeReceive (
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
): Serializable

data class BaseReceive (
    var name: String,
    var onz: Float,
    var company: String,
)

data class JuiceReceive (
    var name: String,
    var onz: Float,
)

data class LiqueurReceive (
    var name: String,
    var onz: Float,
    var company: String,
)

data class EtcReceive (
    var name: String,
    var onz: Float,
)

