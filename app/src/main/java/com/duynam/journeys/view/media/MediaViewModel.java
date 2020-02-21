package com.duynam.journeys.view.media;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.duynam.journeys.data.HTTPMyWife;
import com.duynam.journeys.data.RetrofitImage;
import com.duynam.journeys.model.LinkImageMyWife;
import com.duynam.journeys.model.imagegirl.ImageGirl;
import com.duynam.journeys.model.imagegirl.Photo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MediaViewModel extends ViewModel {

    private List<Photo> myWifeList;
    private OnListUrl onListUrl;
    private WifeAdapter wifeAdapter;
    private Context context;

    public void setOnListUrl(OnListUrl onListUrl) {
        this.onListUrl = onListUrl;
    }

    public MediaViewModel(Context context) {
        this.context = context;
        this.myWifeList = new ArrayList<>();
        wifeAdapter = new WifeAdapter(context, myWifeList);
    }

//    public void LoadListUrl(int page, int perpage){
//        wifeAdapter.removeLoadMore();
//        HTTPMyWife httpMyWife = new HTTPMyWife();
//        httpMyWife.execute("https://www.flickr.com/services/rest/?method=flickr.people.getPublicPhotos&api_key=7a5157fa3e38c0094730ee4c20c78e20&user_id=185405664%40N08&safe_search=1&extras=url_o&per_page="+perpage+"&page="+page+"&format=json&nojsoncallback=1");
//        HTTPMyWife.OnLoadList onLoadList = new HTTPMyWife.OnLoadList() {
//            @Override
//            public void onloadListFinish(List<LinkImageMyWife> list) {
//                onListUrl.onfinish(list);
//                wifeAdapter.setData(list);
//            }
//        };
//        httpMyWife.setOnLoadList(onLoadList);
//    }
        ///services/rest/?method=flickr.people.getPublicPhotos
        // &api_key=7a5157fa3e38c0094730ee4c20c78e20
        // &user_id=185405664%40N08
        // &safe_search=1
        // &extras=url_o
        // &per_page="+perpage+"
        // &page="+page+"
        // &format=json
    // &nojsoncallback=1"

    public void loadImage(int page, int per_page){
        RetrofitImage.getInstance().getImage("flickr.people.getPublicPhotos", "7a5157fa3e38c0094730ee4c20c78e20",
                "185405664@N08", 1, "url_o", per_page, page, "json", 1)
                .enqueue(new Callback<ImageGirl>() {
                    @Override
                    public void onResponse(Call<ImageGirl> call, Response<ImageGirl> response) {
                        onListUrl.onfinish(response.body().getPhotos().getPhoto());
                    }

                    @Override
                    public void onFailure(Call<ImageGirl> call, Throwable t) {

                    }
                });
    }

    public interface OnListUrl{
        void onfinish(List<Photo> wives);
    }

    public void setImage(ImageView imageView,String url){
        Glide.with(context).load(url).into(imageView);
    }

}
