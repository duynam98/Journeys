package com.duynam.journeys.view.journey;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duynam.journeys.R;
import com.duynam.journeys.model.User;
import com.duynam.journeys.UserAdapter;
import com.duynam.journeys.ui.Custorm;

import java.util.ArrayList;
import java.util.List;

public class JourneyFragment extends Fragment {

    private RecyclerView rvListUser;
    private List<User> users;
    private UserAdapter userAdapter;
    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journey, container, false);
        View parent = view.findViewById(R.id.paren_toolbar);
        ImageView imageView = parent.findViewById(R.id.img_menu);
        rvListUser = view.findViewById(R.id.rv_ListUser);

        users = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            users.add(new User("Quân mặt lồn" + i, "0123312001" + i));
        }
        userAdapter = new UserAdapter(getContext(), users);
        rvListUser.setLayoutManager(new LinearLayoutManager(getContext()));
        rvListUser.setAdapter(userAdapter);
        return view;
    }


}
