package com.duynam.journeys.data;

import com.duynam.journeys.model.imagegirl.ImageGirl;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface LockImage {

    ///services/rest/?method=flickr.people.getPublicPhotos
    // &api_key=7a5157fa3e38c0094730ee4c20c78e20
    // &user_id=185405664%40N08
    // &safe_search=1
    // &extras=url_o
    // &per_page="+perpage+"
    // &page="+page+"
    // &format=json
    // &nojsoncallback=1"

    @Headers("Content-Type: application/json")
    @GET("services/rest/")
    Call<ImageGirl> getImage(@Query("method") String method, @Query("api_key") String api_key,
                             @Query("user_id") String user_id, @Query("safe_search") int safe_search,
                             @Query("extras") String extras, @Query("per_page") int per_page,
                             @Query("page") int page, @Query("format") String format,
                             @Query("nojsoncallback") int nojsoncallback);

}
