package com.example.cocktail_pick

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Member (
    @SerializedName("nick_name")
    var nickName: String,
    var email: String,
    @SerializedName("profile_image_url")
    var profileImageURL: String
): Serializable
