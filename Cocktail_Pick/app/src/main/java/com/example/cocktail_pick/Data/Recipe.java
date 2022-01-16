package com.example.cocktail_pick.Data;

import android.media.Image;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.cocktail_pick.Tag;

import java.util.ArrayList;

public class Recipe {
    String title;
    String review;
    String image_url;
    ArrayList<Tag> tags;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Recipe() {
        title = "모스크뮬";
        review = "호불호가 없는 재료들이기에 모두가 좋아할 것 같다. 파인애플이 생각보다 비싸지 않아서 안주 대용으로도 가성비가 좋아서 자주 해먹을만 하다.";
        tags = new ArrayList<>();
//        tags.add(new Tag());
//        tags.add(new Tag());
    }

    public Recipe(String title) {
        this.title = title;
        review = "호불호가 없는 재료들이기에 모두가 좋아할 것 같다. 파인애플이 생각보다 비싸지 않아서 안주 대용으로도 가성비가 좋아서 자주 해먹을만 하다.";
        tags = new ArrayList<>();
//        tags.add(new Tag());
//        tags.add(new Tag());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }
}


