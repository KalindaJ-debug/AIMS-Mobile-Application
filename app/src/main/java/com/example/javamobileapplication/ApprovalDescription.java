package com.example.javamobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ApprovalDescription extends AppCompatActivity {

    private TextView farmerFirst, farmerLast, province, district, region, submitDate, harvest, season, cultivatedAmount;
    private Button btnApprove, btnDeny;
    private String key;
    private float harvestFloat, cultivatedFloat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_description);

        farmerFirst = (TextView) findViewById(R.id.txtFarmerFirst);
        farmerLast = (TextView) findViewById(R.id.txtFarmerLast);
        province = (TextView) findViewById(R.id.txtProvince);
        district = (TextView) findViewById(R.id.txtDistrict);
        region = (TextView) findViewById(R.id.txtRegion);
        submitDate = (TextView) findViewById(R.id.txtSubmitDate);
        harvest = (TextView) findViewById(R.id.txtHarvest);
        season = (TextView) findViewById(R.id.txtSeason);
        cultivatedAmount = (TextView) findViewById(R.id.txtCultivatedLand);
        btnApprove = (Button) findViewById(R.id.btnApprove);
        btnDeny = (Button) findViewById(R.id.btnDeny);

        Intent intent = getIntent();
        farmerFirst.setText(intent.getStringExtra("farmerFirst"));
        farmerLast.setText(intent.getStringExtra("farmerLast"));
        province.setText(intent.getStringExtra("province"));
        district.setText(intent.getStringExtra("district"));
        region.setText(intent.getStringExtra("region"));
        submitDate.setText(intent.getStringExtra("submittedDate"));
        harvest.setText(String.valueOf(intent.getFloatExtra("harvestAmount", 0)));
        cultivatedAmount.setText(String.valueOf(intent.getFloatExtra("cultivatedAmount", 0)));
        season.setText(intent.getStringExtra("season"));
        this.key = intent.getStringExtra("key");

        this.harvestFloat = intent.getFloatExtra("harvestAmount", 0);
        this.cultivatedFloat = intent.getFloatExtra("cultivatedAmount", 0);

        btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Approval approval = new Approval();
                approval.setFirstName(farmerFirst.getText().toString());
                approval.setLastName(farmerLast.getText().toString());
                approval.setProvince(province.getText().toString());
                approval.setDistrict(district.getText().toString());
                approval.setRegion(region.getText().toString());
                approval.setSubmitDate(submitDate.getText().toString());
                approval.setHarvestAmount(harvestFloat);
                approval.setCultivatedAmount(cultivatedFloat);
                approval.setSeason(season.getText().toString());
                approval.setStatus(1);
                new FirebaseDBHelper().updateApproval(key, approval, new FirebaseDBHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Approval> approvals, List<String> keys) {

                    }

                    @Override
                    public void DataInserted() {

                    }

                    @Override
                    public void DataUpdated() {
                        Toast.makeText(ApprovalDescription.this, "Record Updated" + "Test", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataDeleted() {

                    }
                });
            }
        });

        btnDeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Approval approval = new Approval();
                approval.setFirstName(farmerFirst.getText().toString());
                approval.setLastName(farmerLast.getText().toString());
                approval.setProvince(province.getText().toString());
                approval.setDistrict(district.getText().toString());
                approval.setRegion(region.getText().toString());
                approval.setSubmitDate(submitDate.getText().toString());
                approval.setHarvestAmount(harvestFloat);
                approval.setCultivatedAmount(cultivatedFloat);
                approval.setSeason(season.getText().toString());
                approval.setStatus(2);
                new FirebaseDBHelper().updateApproval(key, approval, new FirebaseDBHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Approval> approvals, List<String> keys) {

                    }

                    @Override
                    public void DataInserted() {

                    }

                    @Override
                    public void DataUpdated() {
                        Toast.makeText(ApprovalDescription.this, "Record Updated" + " request was denied", Toast.LENGTH_LONG).show();
                        ReasonAcitivity();
                    }

                    @Override
                    public void DataDeleted() {

                    }
                });
            }
        });
    }

    public void ReasonAcitivity(){
        Intent intent = new Intent(this, ApprovalReason.class);
        intent.putExtra("farmerFirst", farmerFirst.getText().toString());
        intent.putExtra("farmerLast", farmerLast.getText().toString());
        intent.putExtra("province", province.getText().toString());
        intent.putExtra("district", district.getText().toString());
        intent.putExtra("region", region.getText().toString());
        intent.putExtra("submittedDate", submitDate.getText().toString());
        intent.putExtra("harvestAmount", harvestFloat);
        intent.putExtra("cultivatedAmount", cultivatedFloat);
        intent.putExtra("season", season.getText().toString());
        intent.putExtra("key", key);
        startActivity(intent);
    }
}