package com.duynam.journeys.view.media;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duynam.journeys.databinding.ItemMyWifeBinding;
import com.duynam.journeys.model.imagegirl.Photo;
import com.duynam.journeys.ui.Custorm;

public class WifeHolder extends RecyclerView.ViewHolder {

    private ItemMyWifeBinding itemMyWifeBinding;
    public String link;

    public WifeHolder(@NonNull ItemMyWifeBinding itemMyWifeBinding) {
        super(itemMyWifeBinding.getRoot());
        this.itemMyWifeBinding = itemMyWifeBinding;
    }

    public void setBinDing(Photo wife){
        if (itemMyWifeBinding.getHolder() == null){
            itemMyWifeBinding.setHolder(this);
        }
        link = wife.getUrlO();
        itemMyWifeBinding.setLoadimage(link);
        itemMyWifeBinding.imgMyWife.onLongClick(itemMyWifeBinding.imgMyWife, link);
    }
}
