package com.mobilemovement.firestorebasics.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mobilemovement.firestorebasics.R;
import com.mobilemovement.firestorebasics.models.Users;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emretekin on 08/01/18.
 */

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder> {

    private List<Users> usersList = new ArrayList<>();
    private Context context;

    public UsersListAdapter(List<Users> usersList, Context context) {
        this.usersList = usersList;
        this.context = context;
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
                Toast.makeText(context, "USER ID" + userID, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }


}
