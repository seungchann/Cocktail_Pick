package com.example.cocktail_pick.HomeTab

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cocktail_pick.R

class CustomDialog(
    private var _context: Context
) : Dialog(_context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_custom)
    }
}