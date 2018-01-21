package com.mobilemovement.firestorebasics.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobilemovement.firestorebasics.R;

public class ActivityLoadData extends BaseActivity {

    private FirebaseFirestore firestore;
    private Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);

        firestore = FirebaseFirestore.getInstance();
        createViews();
        setListeners();
    }

    @Override
    protected void createViews() {
        super.createViews();
        btnLoad = findViewById(R.id.btnLoad);
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestore.collection("Users").document("lhNZgbLl42QNMfBr1cxy").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();

                            if (documentSnapshot.exists()) {
                                String username = documentSnapshot.getString("name");
                                Snackbar.make(btnLoad, "username = " + username, Snackbar.LENGTH_LONG).show();

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        finish();
                                    }
                                }, 1500);
                            }
                        }
                    }
                });
            }
        });
    }
}
