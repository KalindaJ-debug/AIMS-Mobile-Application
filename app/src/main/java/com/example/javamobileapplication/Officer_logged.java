package com.example.javamobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Officer_logged extends AppCompatActivity {

    Button btnDataEntry;
    Button approvalButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer_logged);

        //declarations
        btnDataEntry = (Button) findViewById(R.id.button_officer_logged_data_entry);
        btnDataEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Officer_logged.this.openDataEntryActivity();
            }
        });

        approvalButton = (Button) findViewById(R.id.approvalButton);
        btnDataEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Officer_logged.this.openApprovalActivity();
            }
        });
    }//end of on create method

    //button methods
    public void openDataEntryActivity(){
        Intent intent = new Intent(this, DataEntryActivity.class);
        startActivity(intent);
    }//end of method

    public void openApprovalActivity(){
        Intent i = new Intent(getApplicationContext(), Approval_List.class);
        startActivity(i);
    }//end of method

}