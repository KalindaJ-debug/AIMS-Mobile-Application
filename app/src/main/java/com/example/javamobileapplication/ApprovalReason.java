package com.example.javamobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ApprovalReason extends AppCompatActivity {

    private String farmerFirst, farmerLast, province, district, region, submitDate, season, key;
    private float harvestAmount, cultivatedAmount;
    private EditText otherText;
    private RadioGroup optionList;
    private RadioButton otherTextRadio, option1Radio, option2Radio, option3Radio;
    private Button submitReasonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_reason);

        submitReasonData = (Button) findViewById(R.id.btnReason);

        otherTextRadio = (RadioButton) findViewById(R.id.checkRadio);
        option1Radio = (RadioButton) findViewById(R.id.option1Radio);
        option2Radio = (RadioButton) findViewById(R.id.option2Radio);
        option3Radio = (RadioButton) findViewById(R.id.option3Radio);

        optionList = (RadioGroup) findViewById(R.id.optionList);
        optionList.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.option1Radio:
                    case R.id.option3Radio:
                    case R.id.option2Radio:
                        //disableText();
                        break;
                    case R.id.checkRadio:
                        //enableText();
                        break;
                }
            }
        });

        otherText = (EditText) findViewById(R.id.otherText);
        //disableText();
        //oivwehgweg
        ///enableText();

        Intent intent = getIntent();
        this.farmerFirst = intent.getStringExtra("farmerFirst");
        this.farmerLast = intent.getStringExtra("farmerLast");
        this.province = intent.getStringExtra("province");
        this.district = intent.getStringExtra("district");
        this.region = intent.getStringExtra("region");
        this.submitDate = intent.getStringExtra("submittedDate");
        this.season = intent.getStringExtra("season");
        this.harvestAmount = intent.getFloatExtra("harvestAmount", 0);
        this.cultivatedAmount = intent.getFloatExtra("cultivatedAmount", 0);
        this.key = intent.getStringExtra("key");

        submitReasonData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (otherTextRadio.isChecked()) {
                    updateData(otherText.getText().toString());
                } else if (option1Radio.isChecked()) {
                    updateData("Inaccurate Data");
                } else if (option2Radio.isChecked()) {
                    updateData("Redundant Data");
                } else if (option3Radio.isChecked()) {
                    updateData("Test Data");
                }
                mainActivity();
            }
        });
    }

    public void mainActivity() {
        Intent intent1 = new Intent(this, ApprovalMain.class);
        startActivity(intent1);
    }

    public void checkRadioInput(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.option1Radio:
            case R.id.option3Radio:
            case R.id.option2Radio:
                if (checked)
                    disableText();
                    break;
            case R.id.checkRadio:
                if (checked)
                    enableText();
                    break;
        }
    }

    public void checkOtherText(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.checkRadio:
                if (checked)
                    // Pirates are the best
                    break;
        }
    }

    public void updateData(String reason) {
        Approval approval = new Approval();
        approval.setReason(reason);
        approval.setCultivatedAmount(cultivatedAmount);
        approval.setSubmitDate(submitDate);
        approval.setHarvestAmount(harvestAmount);
        approval.setSeason(season);
        approval.setDistrict(district);
        approval.setProvince(province);
        approval.setStatus(2);
        approval.setLastName(farmerLast);
        approval.setFirstName(farmerFirst);
        new FirebaseDBHelper().updateApproval(key, approval, new FirebaseDBHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Approval> approvals, List<String> keys) {

            }

            @Override
            public void DataInserted() {

            }

            @Override
            public void DataUpdated() {
                Toast.makeText(ApprovalReason.this, "Record Updated" + "Test", Toast.LENGTH_LONG).show();
            }

            @Override
            public void DataDeleted() {

            }
        });
    }

    public void enableText() {
        otherText.setFocusable(true);
        otherText.setEnabled(true);
        otherText.setCursorVisible(true);
    }

    public void disableText() {
        otherText.setFocusable(false);
        otherText.setEnabled(false);
        otherText.setCursorVisible(false);
    }
}