package com.duynam.journeys.view.media;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.duynam.journeys.R;
import com.duynam.journeys.Utils.Constant;
import com.duynam.journeys.databinding.ItemLoadBinding;
import com.duynam.journeys.databinding.ItemMyWifeBinding;
import com.duynam.journeys.model.LinkImageMyWife;
import com.duynam.journeys.model.imagegirl.Photo;

import java.util.List;

public class WifeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Photo> myWifeList;
    ItemMyWifeBinding binding;

    public WifeAdapter(Context context, List<Photo> myWifeList) {
        this.context = context;
        this.myWifeList = myWifeList;
    }

    public void setData(List<Photo> data) {
        if (data != null) {
            myWifeList.clear();
            myWifeList.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addData(List<Photo> datas){
        int oldposition = myWifeList.size();
        if (datas != null && datas.size() > 0){
            myWifeList.addAll(datas);
            notifyItemRangeChanged(oldposition, myWifeList.size());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == Constant.IMAGE) {
            binding = DataBindingUtil.inflate(inflater, R.layout.item_my_wife, parent, false);
            return new WifeHolder(binding);
        } else {
            ItemLoadBinding loadBinding = DataBindingUtil.inflate(inflater, R.layout.item_load, parent, false);
            return new LoadMoreHolder(loadBinding);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof WifeHolder) {
            ((WifeHolder) holder).setBinDing(myWifeList.get(position));
        }else if (holder instanceof LoadMoreHolder){

        }
    }

    @Override
    public int getItemCount() {
        return myWifeList.size();
    }

    public void addLoadMore() {
        myWifeList.add(null);
    }

    public void removeLoadMore() {
        if (myWifeList == null || myWifeList.size() == 0)
            return;
        if (myWifeList.get(myWifeList.size() - 1) == null) {
            myWifeList.remove(myWifeList.size() - 1);
            notifyItemRemoved(myWifeList.size() - 1);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (myWifeList.get(position) == null) {
            return Constant.LOAD;
        } else {
            return Constant.IMAGE;
        }
    }
}
