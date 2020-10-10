//package com.example.javamobileapplication;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class ApprovalDescription extends AppCompatActivity {
//
//    TextView farmerFirst, farmerLast, district, region, submitDate, harvest;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_approval_description);
//
//        farmerFirst = (TextView) findViewById(R.id.txtFarmerFirst);
//        farmerLast = (TextView) findViewById(R.id.txtFarmerLast);
//        district = (TextView) findViewById(R.id.txtDistrict);
//        region = (TextView) findViewById(R.id.txtRegion);
//        submitDate = (TextView) findViewById(R.id.txtSubmitDate);
//        harvest = (TextView) findViewById(R.id.txtHarvest);
//
//        Intent intent = getIntent();
//        farmerFirst.setText(intent.getStringExtra("farmerFirst"));
//        farmerLast.setText(intent.getStringExtra("farmerLast"));
//        district.setText(intent.getStringExtra("district"));
//        region.setText(intent.getStringExtra("region"));
//        submitDate.setText(intent.getStringExtra("submittedDate"));
//        harvest.setText(intent.getStringExtra("harvest"));
//    }
//
//    public void approve(View view){
//        showToast("Application Approved");
//        Intent intent = new Intent(this, ApprovalMain.class);
//        startActivity(intent);
//    }
//
//    public void decline(View view){
//        showToast("Application Declined");
//        Intent intent = new Intent(this, ApprovalMain.class);
//        startActivity(intent);
//    }
//
//    public void showToast(CharSequence text){
//        Context context = getApplicationContext();
//        int duration = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();
//    }
//}