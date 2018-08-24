package com.padhuga.officeautomation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.padhuga.officeautomation.R;
import com.padhuga.officeautomation.tables.UserTable;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    class UserViewHolder extends RecyclerView.ViewHolder {
        private final TextView userItemView;

        private UserViewHolder(View itemView) {
            super(itemView);
            userItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater layoutInflater;
    private List<UserTable> userTables; // Cached copy of words

    public UserListAdapter(Context context) { layoutInflater = LayoutInflater.from(context); }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        UserTable current = userTables.get(position);
        holder.userItemView.setText(current.getLoginName());
    }

    public void setUsers(List<UserTable> userTable){
        userTables = userTable;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (userTables != null)
            return userTables.size();
        else return 0;
    }
}



