package com.example.cocktail_pick.HomeTab

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cocktail_pick.R
import com.example.cocktail_pick.databinding.DialogCustomBinding
import vadiole.colorpicker.ColorModel
import vadiole.colorpicker.ColorPickerDialog

class CustomDialog(
    private var _context: Context,
    private var fragmentManager: FragmentManager,
) : Dialog(_context) {

    private lateinit var glassAdapter: GlassBtnAdapter
    private lateinit var firstGranishAdapter: FirstGarnishAdapter
    private lateinit var secondGarnishAdapter: SecondGarnishAdapter
    private lateinit var iceAdapter: IceBtnAdapter
    private lateinit var binding: DialogCustomBinding

    var glass = "칵테일 글라스"
    var ice = 0
    var garnishFirst = "체리"
    var garnishSecond = "허브"
    var color = "#f9eeba"

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding = DialogCustomBinding.inflate(layoutInflater)

        setGlassAdapter()
        setGarnishAdapter()
        setSecondGarnishAdapter()
        setIceAdapter()

        binding.customImage.garnishSecond.setOnClickListener {
            val colorPicker: ColorPickerDialog = ColorPickerDialog.Builder()
                .setInitialColor(Color.parseColor(color))
                .setColorModel(ColorModel.RGB)
                .setColorModelSwitchEnabled(true)
                .setButtonOkText(android.R.string.ok)
                .setButtonCancelText(android.R.string.cancel)
                .onColorSelected { color: Int ->
                    this.color = "#${"%06X".format(color + 16777216)}"
                    binding.customImage.liquid.setColorFilter(color)
                }
                .create()

            colorPicker.show(fragmentManager, "color_picker")
        }

        binding.createBtn.setOnTouchListener { _: View, event: MotionEvent ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.createBtn.setRotation(30f)
                    true
                }
                MotionEvent.ACTION_UP -> {
                    binding.createBtn.setRotation(-30f)
                    dismiss()
                    true
                }
                else -> true
            }
        }

        setContentView(binding.root)
    }

    fun setGlassAdapter() {
        glassAdapter = GlassBtnAdapter(context)
        glassAdapter.setOnClickGlassItem = {
            glass = it
            setGlass()
        }
        binding.glassBtn.adapter = glassAdapter
        binding.glassBtn.layoutManager = GridLayoutManager(context, 3)
    }

    fun setGarnishAdapter() {
        firstGranishAdapter = FirstGarnishAdapter(context)
        firstGranishAdapter.setOnClickFirstGarnishItem = {
            garnishFirst = it
            setGarnishFirst()
        }
        binding.firstGarnishBtn.adapter = firstGranishAdapter
        binding.firstGarnishBtn.layoutManager = GridLayoutManager(context, 3)
    }

    fun setSecondGarnishAdapter() {
        secondGarnishAdapter = SecondGarnishAdapter(context)
        secondGarnishAdapter.setOnClickSecondGarnishItem = {
            garnishSecond = it
            setGarnishSecond()
        }
        binding.secondGarnishBtn.adapter = secondGarnishAdapter
        binding.secondGarnishBtn.layoutManager = GridLayoutManager(context, 3)
    }

    fun setIceAdapter() {
        iceAdapter = IceBtnAdapter(context)
        iceAdapter.setOnClickIceItem = {
            ice = it
            setIce()
        }
        binding.iceBtn.adapter = iceAdapter
        binding.iceBtn.layoutManager = GridLayoutManager(context, 3)
    }

    fun setGlass(){
        when(glass){
            "칵테일 글라스" -> {
                binding.customImage.glass.setImageResource(R.drawable.glass_cocktail)
                binding.customImage.liquid.setImageResource(R.drawable.liquid_cocktail)
                setIce()
                setGarnishFirst()
                setGarnishSecond()
            }
            "소서형 샴폐인" -> {
                binding.customImage.glass.setImageResource(R.drawable.glass_champagne)
                binding.customImage.liquid.setImageResource(R.drawable.liquid_champagne)
                setIce()
                setGarnishFirst()
                setGarnishSecond()
            }
            "사워 글라스" -> {
                binding.customImage.glass.setImageResource(R.drawable.glass_sour)
                binding.customImage.liquid.setImageResource(R.drawable.liquid_sour)
                setIce()
                setGarnishFirst()
                setGarnishSecond()
            }
            "풋티스 필스너" -> {
                binding.customImage.glass.setImageResource(R.drawable.glass_pilsner)
                binding.customImage.liquid.setImageResource(R.drawable.liquid_pilsner)
                setIce()
                setGarnishFirst()
                setGarnishSecond()
            }
            "셰리 와인 글라스" -> {
                binding.customImage.glass.setImageResource(R.drawable.glass_sherry_wine)
                binding.customImage.liquid.setImageResource(R.drawable.liquid_sherry_wine)
                setIce()
                setGarnishFirst()
                setGarnishSecond()
            }
            "와인 글라스" -> {
                binding.customImage.glass.setImageResource(R.drawable.glass_wine)
                binding.customImage.liquid.setImageResource(R.drawable.liquid_wine)
                setIce()
                setGarnishFirst()
                setGarnishSecond()
            }
            "리큐르 글라스" -> {
                binding.customImage.glass.setImageResource(R.drawable.glass_liqueur)
                binding.customImage.liquid.setImageResource(R.drawable.liquid_liqueur)
                setIce()
                setGarnishFirst()
                setGarnishSecond()
            }
            "올드 패션 글라스" -> {
                binding.customImage.glass.setImageResource(R.drawable.glass_old_fashion)
                binding.customImage.liquid.setImageResource(R.drawable.liquid_old_fashion)
                setIce()
                setGarnishFirst()
                setGarnishSecond()
            }
            "하이볼 글라스" -> {
                binding.customImage.glass.setImageResource(R.drawable.glass_highball)
                binding.customImage.liquid.setImageResource(R.drawable.liquid_highball)
                setIce()
                setGarnishFirst()
                setGarnishSecond()
            }
            "탐 콜린스 글라스" -> {
                binding.customImage.glass.setImageResource(R.drawable.glass_tom_callins)
                binding.customImage.liquid.setImageResource(R.drawable.liquid_tom_callins)
                setIce()
                setGarnishFirst()
                setGarnishSecond()
            }
        }
        binding.customImage.liquid.setColorFilter(Color.parseColor(color))
    }

    fun setIce(){
        var iceView = binding.customImage.ice
        when (glass){
            "칵테일 글라스", "소서형 샴폐인" -> {
                when(ice){
                    0 -> iceView.setImageResource(R.drawable.ice_pieces_cocktail_champagne)
                    1 -> iceView.setImageResource(R.drawable.ice_circle_cocktail_champagne)
                    2 -> iceView.setImageResource(R.drawable.ice_square_cocktail_champagne)
                }
            }
            "사워 글라스", "리큐르 글라스", "와인 글라스", "셰리 와인 글라스", "풋티스 필스너" -> {
                when(ice){
                    0 -> iceView.setImageResource(R.drawable.ice_pieces_liqueur_wine_sherry_pilsner_sour)
                    1 -> iceView.setImageResource(R.drawable.ice_circle_liqueur_wine_sherry_pilsner_sour)
                    2 -> iceView.setImageResource(R.drawable.ice_square_liqueur_wine_sherry_pilsner_sour)
                }
            }
            "올드 패션 글라스", "탐 콜린스 글라스", "하이볼 글라스" -> {
                when(ice){
                    0 -> iceView.setImageResource(R.drawable.ice_pieces_tom_collins_highball_old_fashion)
                    1 -> iceView.setImageResource(R.drawable.ice_circle_tom_collins_highball_old_fashion)
                    2 -> iceView.setImageResource(R.drawable.ice_square_tom_collins_highball_old_fashion)
                }
            }
        }
    }

    fun setGarnishFirst(){
        val garnishView = binding.customImage.garnishFirst
        when(glass){
            "칵테일 글라스", "소서형 샴폐인" -> {
                when(garnishFirst){
                    "체리" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_cherry_cocktail_champagne)
                    }
                    "허브" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_hurb_cocktail_champagne)
                    }
                    "오렌지" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_orange_cocktail_champagne)
                    }
                    "자몽" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_zamong_cocktail_champagne)
                    }
                    "라임" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_lime_cocktail_champagne)
                    }
                    "x" -> {
                        garnishView.visibility= View.INVISIBLE
                    }
                }
            }
            "사워 글라스", "리큐르 글라스", "풋티스 필스너", "와인 글라스", "셰리 와인 글라스" -> {
                when(garnishFirst){
                    "체리" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_cherry_liqueur_wine_sherry_pilsner_sour)
                    }
                    "허브" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_hurb_liqueur_wine_sherry_pilsner_sour)
                    }
                    "오렌지" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_orange_liqueur_wine_sherry_pilsner_sour)
                    }
                    "자몽" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_zamong_liqueur_wine_sherry_pilsner_sour)
                    }
                    "라임" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_lime_liqueur_wine_sherry_pilsner_sour)
                    }
                    "x" -> {
                        garnishView.visibility= View.INVISIBLE
                    }
                }
            }

            "올드 패션 글라스" -> {
                when(garnishFirst){
                    "체리" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_cherry_old_fashion)
                    }
                    "허브" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_hurb_old_fashion)
                    }
                    "오렌지" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_orange_old_fashion)
                    }
                    "자몽" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_zamong_old_fashion)
                    }
                    "라임" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_lime_old_fashion)
                    }
                    "x" -> {
                        garnishView.visibility= View.INVISIBLE
                    }
                }
            }
            "하이볼 글라스" -> {
                when(garnishFirst){
                    "체리" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_cherry_highball)
                    }
                    "허브" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_hurb_highball_tom_collins)
                    }
                    "오렌지" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_orange_highball_tom_collins)
                    }
                    "자몽" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_zamong_highball_tom_collins)
                    }
                    "라임" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_lime_highball_tom_collins)
                    }
                    "x" -> {
                        garnishView.visibility= View.INVISIBLE
                    }
                }
            }
            "탐 콜린스 글라스" -> {
                when(garnishFirst){
                    "체리" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_cherry_tom_collins)
                    }
                    "허브" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_hurb_highball_tom_collins)
                    }
                    "오렌지" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_orange_highball_tom_collins)
                    }
                    "자몽" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_zamong_highball_tom_collins)
                    }
                    "라임" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_lime_highball_tom_collins)
                    }
                    "x" -> {
                        garnishView.visibility= View.INVISIBLE
                    }
                }
            }
        }
    }

    fun setGarnishSecond() {
        val garnishView = binding.customImage.garnishSecond
        when (glass) {
            "칵테일 글라스", "소서형 샴폐인" -> {
                when (garnishSecond) {
                    "체리" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_cherry_cocktail_champagne)
                    }
                    "허브" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_hurb_cocktail_champagne)
                    }
                    "오렌지" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_orange_cocktail_champagne)
                    }
                    "자몽" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_zamong_cocktail_champagne)
                    }
                    "라임" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_lime_cocktail_champagne)
                    }
                    "x" -> {
                        garnishView.visibility = View.INVISIBLE
                    }
                }
            }
            "사워 글라스", "리큐르 글라스", "풋티스 필스너", "와인 글라스", "셰리 와인 글라스" -> {
                when (garnishSecond) {
                    "체리" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_cherry_liqueur_wine_sherry_pilsner_sour)
                    }
                    "허브" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_hurb_liqueur_wine_sherry_pilsner_sour)
                    }
                    "오렌지" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_orange_liqueur_wine_sherry_pilsner_sour)
                    }
                    "자몽" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_zamong_liqueur_wine_sherry_pilsner_sour)
                    }
                    "라임" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_lime_liqueur_wine_sherry_pilsner_sour)
                    }
                    "x" -> {
                        garnishView.visibility = View.INVISIBLE
                    }
                }
            }

            "올드 패션 글라스" -> {
                when (garnishSecond) {
                    "체리" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_cherry_old_fashion)
                    }
                    "허브" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_hurb_old_fashion)
                    }
                    "오렌지" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_orange_old_fashion)
                    }
                    "자몽" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_zamong_old_fashion)
                    }
                    "라임" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_lime_old_fashion)
                    }
                    "x" -> {
                        garnishView.visibility = View.INVISIBLE
                    }
                }
            }
            "하이볼 글라스" -> {
                when (garnishSecond) {
                    "체리" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_cherry_highball)
                    }
                    "허브" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_hurb_highball_tom_collins)
                    }
                    "오렌지" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_orange_highball_tom_collins)
                    }
                    "자몽" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_zamong_highball_tom_collins)
                    }
                    "라임" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_lime_highball_tom_collins)
                    }
                    "x" -> {
                        garnishView.visibility = View.INVISIBLE
                    }
                }
            }
            "탐 콜린스 글라스" -> {
                when (garnishSecond) {
                    "체리" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_cherry_tom_collins)
                    }
                    "허브" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_hurb_highball_tom_collins)
                    }
                    "오렌지" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_orange_highball_tom_collins)
                    }
                    "자몽" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_zamong_highball_tom_collins)
                    }
                    "라임" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_lime_highball_tom_collins)
                    }
                    "x" -> {
                        garnishView.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

}