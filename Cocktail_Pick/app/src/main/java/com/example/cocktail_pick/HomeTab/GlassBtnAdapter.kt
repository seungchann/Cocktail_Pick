package com.example.cocktail_pick.HomeTab

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktail_pick.databinding.ItemCustomBtnBinding

class GlassBtnAdapter(
    private var context: Context,
) : RecyclerView.Adapter<GlassBtnAdapter.ViewHolder>() {

    var setOnClickGlassItem: ((String) -> Unit)? = null
    private var glassBtn = mutableListOf<String>(
        "칵테일 글라스"
        , "소서형 샴폐인"
        , "사워 글라스"
        , "풋티스 필스너"
        , "셰리 와인 글라스"
        , "와인 글라스"
        , "리큐르 글라스"
        , "올드 패션 글라스"
        , "하이볼 글라스"
        , "탐 콜린스 글라스")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlassBtnAdapter.ViewHolder {
        val binding = ItemCustomBtnBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = glassBtn.size

    override fun onBindViewHolder(holder: GlassBtnAdapter.ViewHolder, position: Int) {
        holder.bind(glassBtn[position])
    }

    inner class ViewHolder(private val binding: ItemCustomBtnBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(btn: String){
            binding.btnName.text = btn
            binding.btnName.setOnClickListener {
                setOnClickGlassItem?.invoke(btn)
            }
        }
    }
}