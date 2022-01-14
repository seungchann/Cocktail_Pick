package com.example.cocktail_pick.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cocktail_pick.R
import com.example.cocktail_pick.RetrofitService
import com.example.cocktail_pick.SearchTab.SearchTabFragment
import com.example.cocktail_pick.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    /*
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.constraintLayout, SearchTabFragment())
            .commit()

        /*
        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
        viewModel.dataList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
        })
        viewModel.getAllData()
         */
    }
}

