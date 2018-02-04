package com.mobilemovement.firestorebasics.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mobilemovement.firestorebasics.R;

public class ActivityMain extends BaseActivity {

    Button btnAddingData, btnLoadData, btnListData;
    ImageView ivLogo;

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
        ivLogo = findViewById(R.id.ivLogo);
        btnAddingData = findViewById(R.id.btnAddingData);
        btnLoadData = findViewById(R.id.btnLoadData);
        btnListData = findViewById(R.id.btnListData);
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        btnAddingData.setOnClickListener(this);
        btnLoadData.setOnClickListener(this);
        btnListData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnAddingData:
                startActivity(new Intent(ActivityMain.this, ActivityAddingData.class));
                break;
            case R.id.btnLoadData:
                startActivity(new Intent(ActivityMain.this, ActivityLoadData.class));
                break;
            case R.id.btnListData:
                Intent intent = new Intent(ActivityMain.this, ActivityListData.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(ActivityMain.this, ivLogo, ViewCompat.getTransitionName(ivLogo));
                startActivity(intent, options.toBundle());
                break;
        }
    }
}
