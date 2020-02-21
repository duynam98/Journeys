package com.duynam.journeys.data;

import android.os.AsyncTask;

import com.duynam.journeys.model.LinkImageMyWife;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HTTPMyWife extends AsyncTask<String, Void, List<LinkImageMyWife>> {

    private List<LinkImageMyWife> mywifeList;
    private OnLoadList onLoadList;

    public HTTPMyWife() {
        this.mywifeList = new ArrayList<>();
    }

    public void setOnLoadList(OnLoadList onLoadList) {
        this.onLoadList = onLoadList;
    }

    @Override
    protected List<LinkImageMyWife> doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String line;
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            }
            if (builder.toString() != null) {
                JSONObject object = new JSONObject(builder.toString());
                JSONObject photos = object.getJSONObject("photos");
                JSONArray photo = photos.getJSONArray("photo");
                for (int i = 0; i <= photo.length(); i++) {
                    JSONObject mywife = photo.getJSONObject(i);
                    String url_o = mywife.optString("url_o");
                    mywifeList.add(new LinkImageMyWife(url_o));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mywifeList;
    }

    @Override
    protected void onPostExecute(List<LinkImageMyWife> linkImageMyWives) {
        super.onPostExecute(linkImageMyWives);
        onLoadList.onloadListFinish(linkImageMyWives);
    }

    public interface OnLoadList{
        void onloadListFinish(List<LinkImageMyWife> list);
    }

}
