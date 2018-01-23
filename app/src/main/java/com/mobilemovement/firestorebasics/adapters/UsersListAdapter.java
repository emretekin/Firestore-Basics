package com.mobilemovement.firestorebasics.adapters;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobilemovement.firestorebasics.R;
import com.mobilemovement.firestorebasics.models.Users;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emretekin on 08/01/18.
 */

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder> {

    private List<Users> usersList = new ArrayList<>();

    public UsersListAdapter(List<Users> usersList) {
        this.usersList = usersList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View mView;
        public TextView name;
        public TextView status;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            name = mView.findViewById(R.id.tvName);
            status = mView.findViewById(R.id.tvStatus);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(usersList.get(position).getName());
        holder.status.setText(usersList.get(position).getStatus());

        final String userID = usersList.get(position).userId;

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "USER ID = " + userID, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }


}
