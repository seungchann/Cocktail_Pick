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
import com.example.cocktail_pick.HomeTab.CustomHandler as CustomHandler


class CustomDialog(
    private var _context: Context,
    private var fragmentManager: FragmentManager,
) : Dialog(_context) {

    private lateinit var glassAdapter: GlassBtnAdapter
    private lateinit var firstGranishAdapter: FirstGarnishAdapter
    private lateinit var secondGarnishAdapter: SecondGarnishAdapter
    private lateinit var iceAdapter: IceBtnAdapter
    private lateinit var binding: DialogCustomBinding
    private lateinit var handler: CustomHandler

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
        handler = CustomHandler()

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
                    false
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
            handler.setGlass(glass, ice, garnishFirst, garnishSecond, color, binding)
        }
        binding.glassBtn.adapter = glassAdapter
        binding.glassBtn.layoutManager = GridLayoutManager(context, 3)
    }

    fun setGarnishAdapter() {
        firstGranishAdapter = FirstGarnishAdapter(context)
        firstGranishAdapter.setOnClickFirstGarnishItem = {
            garnishFirst = it
            handler.setGarnishFirst(glass, garnishFirst, binding)
        }
        binding.firstGarnishBtn.adapter = firstGranishAdapter
        binding.firstGarnishBtn.layoutManager = GridLayoutManager(context, 3)
    }

    fun setSecondGarnishAdapter() {
        secondGarnishAdapter = SecondGarnishAdapter(context)
        secondGarnishAdapter.setOnClickSecondGarnishItem = {
            garnishSecond = it
            handler.setGarnishSecond(glass, garnishSecond, binding)
        }
        binding.secondGarnishBtn.adapter = secondGarnishAdapter
        binding.secondGarnishBtn.layoutManager = GridLayoutManager(context, 3)
    }

    fun setIceAdapter() {
        iceAdapter = IceBtnAdapter(context)
        iceAdapter.setOnClickIceItem = {
            ice = it
            handler.setIce(glass, ice, binding)
        }
        binding.iceBtn.adapter = iceAdapter
        binding.iceBtn.layoutManager = GridLayoutManager(context, 3)
    }
}