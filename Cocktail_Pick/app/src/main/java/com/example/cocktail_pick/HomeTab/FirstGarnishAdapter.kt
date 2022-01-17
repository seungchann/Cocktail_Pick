package com.example.cocktail_pick.HomeTab

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktail_pick.HomeTab.FirstGarnishAdapter.*
import com.example.cocktail_pick.databinding.DialogCustomBinding
import com.example.cocktail_pick.databinding.ItemCustomBtnBinding

class FirstGarnishAdapter(private var context: Context) : RecyclerView.Adapter<ViewHolder>() {
    private var garnishBtn = mutableListOf<String>(
        "x"
        , "라임"
        , "오렌지"
        , "자몽"
        , "허브")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCustomBtnBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = garnishBtn.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(garnishBtn[position])
    }

    inner class ViewHolder(private val binding: ItemCustomBtnBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(btn: String) {
            binding.btnName.text = btn
        }
    }
}