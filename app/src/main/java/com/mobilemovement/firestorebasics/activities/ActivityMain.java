package com.mobilemovement.firestorebasics.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mobilemovement.firestorebasics.R;

public class ActivityMain extends BaseActivity {

    Button btnAddingData;
    Button btnLoadData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createViews();
        setListeners();
    }

    @Override
    protected void createViews() {
        super.createViews();
        btnAddingData = findViewById(R.id.btnAddingData);
        btnLoadData = findViewById(R.id.btnLoadData);
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        btnAddingData.setOnClickListener(this);
        btnLoadData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnAddingData:
                startActivity(new Intent(ActivityMain.this, ActivityAddingData.class));
                break;

            case R.id.btnLoadData:
                break;
        }
    }
}
