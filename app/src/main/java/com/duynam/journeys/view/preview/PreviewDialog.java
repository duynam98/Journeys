package com.duynam.journeys.view.preview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.duynam.journeys.R;
import com.duynam.journeys.databinding.DialogPreviewWifeBinding;
import com.duynam.journeys.view.media.MediaViewModel;

public class PreviewDialog extends Dialog {

    private DialogPreviewWifeBinding binding;
    private MediaViewModel mediaViewModel;
    private String url;

    public PreviewDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_preview_wife, null, false);
        setContentView(binding.getRoot());
        initDataBinding();
        LoadImage();
    }

    private void initDataBinding() {
        mediaViewModel = new MediaViewModel(getContext());
    }

    private void LoadImage() {
        mediaViewModel.setImage(binding.imgPreview, url);
    }

    public void showImage(String url) {
        Glide.with(getContext()).load(url).into(binding.imgPreview);
        show();
    }

    public void dismissImage(){
        dismiss();
    }

}
