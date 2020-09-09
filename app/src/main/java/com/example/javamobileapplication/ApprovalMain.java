package com.example.javamobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ApprovalMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_main);
    }

    public void nextActivity(View view) {
        Intent intent = new Intent(this, ApprovalDescription.class);
        startActivity(intent);
    }
}