package com.duynam.journeys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;

import com.duynam.journeys.databinding.ItemUserBinding;
import com.duynam.journeys.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private Context context;
    private List<User> userList;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    public void setData(List<User> users) {
        if (users != null) {
            userList.clear();
            userList.addAll(users);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemUserBinding itemUserBinding = DataBindingUtil.inflate(inflater, R.layout.item_user, parent, false);
        return new UserHolder(itemUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        holder.setBinDing(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {

        public ObservableField<String> username, phone;
        private ItemUserBinding itemUserBinding;

        public UserHolder(@NonNull ItemUserBinding itemUserBinding) {
            super(itemUserBinding.getRoot());
            this.itemUserBinding = itemUserBinding;
            this.username = new ObservableField<>();
            this.phone = new ObservableField<>();
        }

        public void setBinDing(User user) {
            if (itemUserBinding.getUserholder() == null) {
                itemUserBinding.setUserholder(this);
            }
            username.set(user.getName());
            phone.set(user.getPhone());
        }

    }
}
