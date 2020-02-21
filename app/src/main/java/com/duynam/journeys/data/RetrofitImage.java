package com.duynam.journeys.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitImage {

    public static LockImage lockImage;
    public static LockImage getInstance(){
        if (lockImage == null){
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.flickr.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            lockImage = retrofit.create(LockImage.class);
        }
        return lockImage;
    }
}
