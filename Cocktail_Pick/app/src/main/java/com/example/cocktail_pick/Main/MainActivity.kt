package com.example.cocktail_pick.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cocktail_pick.R
import com.example.cocktail_pick.RetrofitService
import com.example.cocktail_pick.SearchTab.SearchTabFragment
import com.example.cocktail_pick.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    lateinit var tab2: SearchTabFragment

    /*
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tab2 = SearchTabFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.main_fragment_layout, tab2)
            .commit()

        main_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> { }
                    1 -> { replaceView(tab2) }
                    2 -> { }
                    3 -> { }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) { }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> { }
                    1 -> {
                        tab2 = SearchTabFragment()
                        replaceView(tab2)
                    }
                    2 -> { }
                }

            }
        })

        /*
        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
        viewModel.dataList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
        })
        viewModel.getAllData()
         */
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

