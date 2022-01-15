package com.example.cocktail_pick.Data;

import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class Tag {
    Color color;
    String taste;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Tag() {
        this.color = Color.valueOf(255, 0, 0);
        this.taste = "달달한";
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }
}
