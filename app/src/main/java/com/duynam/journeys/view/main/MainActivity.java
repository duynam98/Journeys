package com.duynam.journeys.view.main;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import com.duynam.journeys.view.media.MediaFragment;
import com.duynam.journeys.R;
import com.duynam.journeys.view.journey.JourneyFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    private ConstraintLayout paren;
    private BottomNavigationView navBottom;
    private FragmentTransaction transaction;
    private MediaFragment fragmentMedia;
    private JourneyFragment journeyFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fragmentMedia = new MediaFragment();
        journeyFragment = new JourneyFragment();
        navBottom.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.paren, journeyFragment);
        transaction.commit();

    }


    private void initView() {
        paren = findViewById(R.id.paren);
        navBottom = findViewById(R.id.nav_bottom);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.menu_media:
                            transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.paren, fragmentMedia);
                            transaction.commit();
                            break;
                        case R.id.menu_journey:
                            transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.paren, journeyFragment);
                            transaction.commit();
                            break;
                    }
                    return true;
                }
            };

}
