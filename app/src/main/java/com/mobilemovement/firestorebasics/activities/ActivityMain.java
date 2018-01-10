package com.mobilemovement.firestorebasics.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

public class ActivityMain extends AppCompatActivity {

    private static final String TAG = "FireLog";

    private List<Users> usersList = new ArrayList<>();

    private RecyclerView rvUsers;
    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvUsers = findViewById(R.id.rvUsers);
        mFirestore = FirebaseFirestore.getInstance();


        final UsersListAdapter usersListAdapter = new UsersListAdapter(usersList, getApplicationContext());
        rvUsers.setHasFixedSize(true);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        rvUsers.setAdapter(usersListAdapter);

        mFirestore.collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error: " + e.getMessage());
                }


                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        String userID = doc.getDocument().getId();

                        Users users = doc.getDocument().toObject(Users.class).withId(userID);
                        usersList.add(users);
                        usersListAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
