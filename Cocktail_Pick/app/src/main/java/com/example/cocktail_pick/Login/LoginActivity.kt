package com.example.cocktail_pick.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cocktail_pick.Main.MainActivity
import com.example.cocktail_pick.Member
import com.example.cocktail_pick.R
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        KakaoSdk.init(this, "65fb49aeedace7bb1c61e82acaf37669")
        var keyHash = Utility.getKeyHash(this)
        Log.d("KaKao_HASH", keyHash)

        supportFragmentManager.beginTransaction()
            .replace(R.id.login_frame_layout, LoginFragment())
            .addToBackStack(null)
            .commit()
    }

    fun moveToMainActivity() {
        val nextIntent = Intent(this, MainActivity::class.java)
        startActivity(nextIntent)
    }
}