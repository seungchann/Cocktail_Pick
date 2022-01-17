package com.example.cocktail_pick.HomeTab

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cocktail_pick.R
import com.example.cocktail_pick.databinding.DialogCustomBinding

class CustomDialog(
    private var _context: Context
) : Dialog(_context) {

    private lateinit var glassAdapter: GlassBtnAdapter
    private lateinit var firstGranishAdapter: FirstGarnishAdapter
    private lateinit var secondGarnishAdapter: SecondGarnishAdapter
    private lateinit var iceAdapter: IceBtnAdapter
    private lateinit var binding: DialogCustomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_custom)

        binding = DialogCustomBinding.inflate(layoutInflater)

        setGlassAdapter()
        setGarnishAdapter()
        setSecondGarnishAdapter()
        setIceAdapter()

    }

    fun setGlassAdapter() {
        glassAdapter = GlassBtnAdapter(context)
        binding.glassBtn.adapter = glassAdapter
        binding.glassBtn.layoutManager = GridLayoutManager(context, 3)
    }

    fun setGarnishAdapter() {
        firstGranishAdapter = FirstGarnishAdapter(context)
        binding.firstGarnishBtn.adapter = firstGranishAdapter
        binding.firstGarnishBtn.layoutManager = GridLayoutManager(context, 3)
    }

    fun setSecondGarnishAdapter() {
        secondGarnishAdapter = SecondGarnishAdapter(context)
        binding.firstGarnishBtn.adapter = firstGranishAdapter
        binding.firstGarnishBtn.layoutManager = GridLayoutManager(context, 3)
    }

    fun setIceAdapter() {
        iceAdapter = IceBtnAdapter(context)
        binding.iceBtn.adapter = iceAdapter
        binding.iceBtn.layoutManager = GridLayoutManager(context, 3)
    }

}