package com.example.cocktail_pick.Data;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class User {
    String name;
    String profile_url;
    // profile image, email...
    ArrayList<Tag> tags;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public User() {
        name = "술을 좋아하는 무지";
        tags = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            tags.add(new Tag());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }
}
