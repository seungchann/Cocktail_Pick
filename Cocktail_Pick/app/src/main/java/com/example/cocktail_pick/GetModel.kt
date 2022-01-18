package com.example.cocktail_pick

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserReceive (
    var id: Int,
    var email: String,
    @SerializedName("username")
    var userName: String,
    @SerializedName("profile_url")
    var profileURL: String,
    @SerializedName("prefer_recipe_list")
    var preferRecipeList: List<RecipeReceive>,
    @SerializedName("my_tag")
    var myTag: List<String>,
    var recipes: List<RecipeReceive>
): Serializable

data class TagReceive (
    var id: Int,
    var color: String,
    var taste: String,
    var users_name: List<String>
): Serializable

data class PreferUser (
    var username: String,
    var profile_url: String,
): Serializable

data class RecipeReceive (
    var id: Int,
    @SerializedName("user_id")
    var userId: Int,
    var intro: String,
    var alcohol: Int,
    var writer: String,
    var tags: List<Int>,
    @SerializedName("cocktail_name")
    var cocktailName: String,
    @SerializedName("cocktail_color")
    var cocktailColor: String,
    var glass: String,
    var ice: Int,
    @SerializedName("garnish_first")
    var garnishFirst: String,
    @SerializedName("garnish_second")
    var garnishSecond: String,
    var posting: String,
    @SerializedName("base_name")
    var base: String,
    @SerializedName("base_oz")
    var baseOz: Float,
    @SerializedName("liqueur_name")
    var liqueur: String,
    @SerializedName("liqueur_oz")
    var liqueurOz: Float,
    @SerializedName("juice_name")
    var juice: String,
    @SerializedName("juice_oz")
    var juiceOz: Float,
    @SerializedName("etc_name")
    var etc: String,
    @SerializedName("etc_oz")
    var etcOz: Float,
    var like_num: Int,
    var prefer_user_lists: List<List<String>>,
    var step: String,
): Serializable

data class BaseReceive (
    var name: String,
    var onz: Float,
    var company: String,
): Serializable

data class JuiceReceive (
    var name: String,
    var onz: Float,
): Serializable

data class LiqueurReceive (
    var name: String,
    var onz: Float,
    var company: String,
): Serializable

data class EtcReceive (
    var name: String,
    var onz: Float,
): Serializable