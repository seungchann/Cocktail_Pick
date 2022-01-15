package com.example.cocktail_pick.SearchTab;

import java.io.Serializable;

public class Product implements Serializable {
    private String title;
    private String base;

    public Product(String title, String base) {
        this.title = title;
        this.base = base;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBase() {
        return base;
    }

    public void setPrice(String base) {
        this.base = base;
    }
}

