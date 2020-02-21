package com.duynam.journeys.view.media;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duynam.journeys.databinding.ItemLoadBinding;

public class LoadMoreHolder extends RecyclerView.ViewHolder {

    private ItemLoadBinding itemLoadBinding;

    public LoadMoreHolder(@NonNull ItemLoadBinding itemLoadBinding) {
        super(itemLoadBinding.getRoot());
        this.itemLoadBinding = itemLoadBinding;
    }

}
