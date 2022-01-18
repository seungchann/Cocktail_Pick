package com.example.cocktail_pick.Main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktail_pick.HomeTab.SummaryAdapter
import com.example.cocktail_pick.Login.LoginViewModel
import com.example.cocktail_pick.Login.LoginViewModelFactory
import com.example.cocktail_pick.MainRepository
import com.example.cocktail_pick.R
import com.example.cocktail_pick.RecipeReceive
import com.example.cocktail_pick.RetrofitService
import com.example.cocktail_pick.databinding.FragmentHomeBinding
import com.example.cocktail_pick.databinding.FragmentLoginBinding
import com.kakao.sdk.auth.model.OAuthToken
import java.lang.IllegalStateException
import java.util.ArrayList

class RecipeFragment : Fragment() {

    private val TAG = "RecipeFragment"
    private val retrofitService = RetrofitService.getInstance()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private lateinit var summaryAdapter: SummaryAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(getViewModelStoreOwner(), MainViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
        viewModel.loadTagBasedRecipe()

        recyclerView = binding.summaryRecyclerView
        viewModel.tagBasedRecipeList.observe(viewLifecycleOwner, Observer {
            summaryAdapter = SummaryAdapter(activity, (it as ArrayList<RecipeReceive>))
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = summaryAdapter
        })

        // Inflate the layout for this fragment
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
}