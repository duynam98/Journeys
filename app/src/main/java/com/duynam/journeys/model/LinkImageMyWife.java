package com.duynam.journeys.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class LinkImageMyWife {

    public String mywife;

    public LinkImageMyWife(String mywife) {
        this.mywife = mywife;
    }

    public String getMywife() {
        return mywife;
    }

    public void setMywife(String mywife) {
        this.mywife = mywife;
    }

    @BindingAdapter("image")
    public static void loadImage(ImageView imageView, String url_o){
        Glide.with(imageView.getContext()).load(url_o).into(imageView);
    }

}
