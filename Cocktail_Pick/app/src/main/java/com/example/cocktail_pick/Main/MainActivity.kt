package com.example.cocktail_pick.Main

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cocktail_pick.HomeTab.DetailRecipe.DetailRecipeActivity
import com.example.cocktail_pick.HomeTab.HomeTabFragment
import com.example.cocktail_pick.MainRepository
import com.example.cocktail_pick.MyPageTab.MyPageTabFragment
import com.example.cocktail_pick.R
import com.example.cocktail_pick.RecommendTab.RecommendTabFragment
import com.example.cocktail_pick.RetrofitService
import com.example.cocktail_pick.SearchTab.SearchTabFragment
import com.example.cocktail_pick.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private val retrofitService = RetrofitService.getInstance()
    private lateinit var viewModel: MainViewModel

    lateinit var tab1: HomeTabFragment
    lateinit var tab2: SearchTabFragment
    lateinit var tab3: RecommendTabFragment
    lateinit var tab4: MyPageTabFragment

    /*
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)

        viewModel.currentUserEmail = intent.getSerializableExtra("email") as String
//        viewModel.currentUserEmail = "dycha0430@gmail.com"
        viewModel.loadUserAccount()

        tab1 = HomeTabFragment()
        tab2 = SearchTabFragment()
        tab3 = RecommendTabFragment()
        tab4 = MyPageTabFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.main_fragment_layout, tab1)
            .commit()

        main_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> { replaceView(tab1) }
                    1 -> { replaceView(tab2) }
                    2 -> { replaceView(tab3) }
                    3 -> { replaceView(tab4) }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) { }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onTabReselected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> {
                        tab1 = HomeTabFragment()
                        replaceView(tab1)
                    }
                    1 -> {
                        tab2 = SearchTabFragment()
                        replaceView(tab2)
                    }
                    2 -> {
                        tab3 = RecommendTabFragment()
                        replaceView(tab3)
                    }
                    3 -> {
                        tab4 = MyPageTabFragment()
                        replaceView(tab4)
                    }
                }

            }
        })
    }

    private fun replaceView(tab: Fragment) {
        var selectedFragment: Fragment? = null
        selectedFragment = tab
        selectedFragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_layout, it)
                .disallowAddToBackStack()
                .commit()
        }
    }
}

