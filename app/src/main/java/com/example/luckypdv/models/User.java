package com.example.luckypdv.models;

import android.os.Bundle;

public class User {
    private int id;
    private String name;
    private String image;
    private String email;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() { return image;}

    public void setImage(String image) { this.image = image;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("image", image);
        bundle.putString("email", email);

        return bundle;
    }

    public void fromBundle(Bundle bundle){
        this.name = bundle.getString("name");
        this.image = bundle.getString("image");
        this.email = bundle.getString("email");
    }
}

