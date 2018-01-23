package com.mobilemovement.firestorebasics.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.mobilemovement.firestorebasics.R;
import com.mobilemovement.firestorebasics.adapters.UsersListAdapter;
import com.mobilemovement.firestorebasics.models.Users;

import java.util.ArrayList;
import java.util.List;

public class ActivityListData extends BaseActivity {

    private List<Users> usersList;
    private UsersListAdapter usersListAdapter;

    private RecyclerView rvUserList;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        firestore = FirebaseFirestore.getInstance();

        createViews();
        setListeners();
        initRecyclerView();
    }

    @Override
    protected void createViews() {
        super.createViews();
        rvUserList = findViewById(R.id.rvUserList);
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        addSnapshotListener();
    }

    @Override
    protected void initRecyclerView() {
        super.initRecyclerView();
        usersList = new ArrayList<>();
        usersListAdapter = new UsersListAdapter(usersList);
        rvUserList.setLayoutManager(new LinearLayoutManager(this));
        rvUserList.setAdapter(usersListAdapter);
    }

    private void addSnapshotListener(){
        firestore.collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null){
                    Snackbar.make(rvUserList, e.getMessage(), Snackbar.LENGTH_LONG).show();
                }

                for (DocumentChange doc: documentSnapshots.getDocumentChanges()){

                    if (doc.getType() == DocumentChange.Type.ADDED){  //It has three Type of DocumentChange Type.ADDED, Type.MODIFIED, Type.REMOVED

                        Users users = doc.getDocument().toObject(Users.class).withId(doc.getDocument().getId());
                        usersList.add(users);
                        usersListAdapter.notifyDataSetChanged();
                    }

                }
            }
        });
    }
}
