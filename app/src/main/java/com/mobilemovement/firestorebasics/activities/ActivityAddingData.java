package com.mobilemovement.firestorebasics.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobilemovement.firestorebasics.R;

import java.util.HashMap;
import java.util.Map;

public class ActivityAddingData extends AppCompatActivity {

    private EditText etName;
    private Button btnSave;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_data);


        createViews();
        initFirestore();
        setListeners();
    }

    private void createViews() {
        etName = findViewById(R.id.etName);
        btnSave = findViewById(R.id.btnSave);
    }

    private void setListeners() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etName.getText().toString().trim();
                if (!TextUtils.isEmpty(username)) {
                    addData(username);
                } else {
                    Snackbar.make(btnSave, "Please write your username", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void addData(String username) {
        Map<String, String> params = new HashMap<>();
        params.put("name", username);
        params.put("image", "image_link");

        firestore.collection("Users").add(params).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Snackbar.make(btnSave, "Username Added to Firestore.", Snackbar.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(btnSave, "Error", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void initFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }
}
