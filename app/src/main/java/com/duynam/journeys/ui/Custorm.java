package com.duynam.journeys.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class Custorm extends AppCompatImageView {


    private OnClickEvent onClickEvent;

    public void setOnClickEvent(OnClickEvent onClickEvent) {
        this.onClickEvent = onClickEvent;
    }

    public Custorm(Context context) {
        this(context, null);
    }

    public Custorm(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    private float downX;
    private float downY;
    public static final String TAG = "downTime";

    public void onLongClick(Custorm imgMyWife, String url) {

        imgMyWife.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downX = event.getX();
                        downY = event.getY();
                        onClickEvent.OnClickImage(url);
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:
                        float upX = event.getX();
                        float upY = event.getY();
                        long upTime = event.getEventTime();
                        Log.e(TAG, "onUp: " + upTime);
                        if (upX - downX > 15 && upY - downY > 15) {
                            if (onClickEvent != null) {
                                onClickEvent.OnScroll();
                            }
                        } else {
                            if (onClickEvent != null) {
                                onClickEvent.OnClickImage(url);
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }

    public interface OnClickEvent {
        void OnClickImage(String url);
        void OnScroll();
    }

}
