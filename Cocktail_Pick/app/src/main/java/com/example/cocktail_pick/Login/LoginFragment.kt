package com.example.cocktail_pick.Login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.cocktail_pick.MainRepository
import com.example.cocktail_pick.Member
import com.example.cocktail_pick.R
import com.example.cocktail_pick.RetrofitService
import com.example.cocktail_pick.databinding.FragmentLoginBinding
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User
import java.lang.IllegalStateException

class LoginFragment : Fragment() {

    private val TAG = "LoginFragment"
    private val retrofitService = RetrofitService.getInstance()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel
    private lateinit var mCallback: (OAuthToken?, Throwable?) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(getViewModelStoreOwner(), LoginViewModelFactory(MainRepository(retrofitService))).get(LoginViewModel::class.java)

        makeKakaoCallback()
        binding.kakaoButton.setOnClickListener {
            checkKakaoLogin()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun Fragment.getViewModelStoreOwner(): ViewModelStoreOwner = try {
        requireActivity()
    } catch (e: IllegalStateException) {
        this
    }

    private fun makeKakaoCallback() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) { Log.e(TAG, "로그인 실패", error) }
            else if (token != null) {
                Log.i(TAG, "로그인 성공 ${token.accessToken}")
                UserApiClient.instance.me { user, error ->
                    if (error != null) { Log.e(TAG, "사용자 정보 요청 실패", error) }
                    else if (user != null) { makeMemberFromKakaoDB(user) }
                }
            }
        }
        mCallback = callback
    }

    private fun checkKakaoLogin() {
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { _, error ->
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError() == true) { KakaoLogin() }
                    else { Log.e("Kakao_Token", "기타 에러") } //기타 에러
                }
                else {
                    Log.d("Kakao_Token","토큰이 유효합니다.")
                    //토큰 유효성 체크 성공(필요 시 토큰 갱신됨)
                    UserApiClient.instance.me { user, error ->
                        if (error != null) { Log.e(TAG, "사용자 정보 요청 실패", error) }
                        else if (user != null) { makeMemberFromKakaoDB(user) }
                    }
                }
            }
        }
        else { KakaoLogin() }
    }

    fun KakaoLogin() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable((activity as LoginActivity))) {
            UserApiClient.instance.loginWithKakaoTalk((activity as LoginActivity), callback = mCallback)
        } else {
            UserApiClient.instance.loginWithKakaoAccount((activity as LoginActivity), callback = mCallback)
        }
    }

    fun makeMemberFromKakaoDB(user: User) {
        Log.i(TAG, "사용자 정보 요청 성공" +
                "\n이메일: ${user.kakaoAccount?.email}" +
                "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")

        val newMember = Member(user.kakaoAccount?.profile?.nickname ?: "user",
            user.kakaoAccount?.email ?: "@.",
            user.kakaoAccount?.profile?.thumbnailImageUrl ?: "")

        this.viewModel.currentUser = newMember

        (activity as LoginActivity).moveToMainActivity()
    }
}