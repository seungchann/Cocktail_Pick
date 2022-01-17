package com.example.cocktail_pick.HomeTab

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktail_pick.databinding.ItemCustomBtnBinding

class IceBtnAdapter(private var context: Context) : RecyclerView.Adapter<IceBtnAdapter.ViewHolder>() {
    var setOnClickIceItem: ((Int) -> Unit)? = null
    private var iceBtn = mutableListOf<String>(
        "얼음 조각",
        "둥근 얼음",
        "사각 얼음"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCustomBtnBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = iceBtn.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(iceBtn[position])
    }

    inner class ViewHolder(private val binding: ItemCustomBtnBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(btn: String) {
            binding.btnName.text = btn
            binding.btnName.setOnClickListener {
                when (btn) {
                    "얼음 조각" -> setOnClickIceItem?.invoke(0)
                    "둥근 얼음" -> setOnClickIceItem?.invoke(1)
                    "사각 얼음" -> setOnClickIceItem?.invoke(2)
                }
            }
        }

    }
}