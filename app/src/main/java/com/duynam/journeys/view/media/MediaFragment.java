package com.duynam.journeys.view.media;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duynam.journeys.R;
import com.duynam.journeys.Utils.Constant;
import com.duynam.journeys.databinding.FragmentMediaBinding;
import com.duynam.journeys.model.LinkImageMyWife;
import com.duynam.journeys.model.imagegirl.Photo;
import com.duynam.journeys.ui.Custorm;
import com.duynam.journeys.view.preview.PreviewDialog;

import java.util.ArrayList;
import java.util.List;

public class MediaFragment extends Fragment implements MediaViewModel.OnListUrl, Custorm.OnClickEvent {

    private FragmentMediaBinding binding;
    private MediaViewModel mediaViewModel;
    private WifeAdapter wifeAdapter;
    private List<Photo> list;
    private int page = 1, per_page = 40;
    private boolean isLoadMore;
    private GridLayoutManager gridLayoutManager;
    private PreviewDialog previewDialog;
    private Custorm custorm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_media, container, false);
        initDataBinDing();
        get();
        setLayoutRecycleview(binding.rvMedia);
        loadmore(binding.rvMedia);
        setFullItemLoadMore();
        custorm = new Custorm(getContext());
        custorm.setOnClickEvent(this);
        return binding.getRoot();
    }

    private void initDataBinDing() {
        mediaViewModel = new MediaViewModel(getContext());
        mediaViewModel.setOnListUrl(this);
        list = new ArrayList<>();
        wifeAdapter = new WifeAdapter(getContext(), list);
        gridLayoutManager = new GridLayoutManager(getContext(), 4);
        previewDialog = new PreviewDialog(getContext());
    }

    private void get() {
        mediaViewModel.loadImage(page, per_page);
    }

    private void setLayoutRecycleview(RecyclerView view) {
        view.setLayoutManager(gridLayoutManager);
        view.setAdapter(wifeAdapter);
    }

    @Override
    public void onfinish(List<Photo> wives) {
        wifeAdapter.removeLoadMore();
        if (isLoadMore) {
            isLoadMore = false;
            wifeAdapter.addData(wives);
        } else {
            wifeAdapter.addData(wives);
        }
    }

    private void loadmore(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLoadMore) {
                    return;
                }
                int currentPosition = gridLayoutManager.findLastVisibleItemPosition();
                int listsize = wifeAdapter.getItemCount();
                if (currentPosition < listsize - 4) {
                    if (per_page == 500) {
                        page++;
                    } else {
                        per_page = per_page + 20;
                    }
                    wifeAdapter.addLoadMore();
                    isLoadMore = true;
                    mediaViewModel.loadImage(page, per_page);
                }

            }
        });
    }

    private void setFullItemLoadMore() {
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (wifeAdapter.getItemViewType(position) == Constant.LOAD) {
                    return 4;
                } else if (wifeAdapter.getItemViewType(position) == Constant.IMAGE) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    @Override
    public void OnClickImage(String url) {
        if (previewDialog != null) {
            previewDialog.showImage(url);
        }
    }

    @Override
    public void OnScroll() {
        if (previewDialog != null) {
            previewDialog.dismiss();
        }
    }
}
