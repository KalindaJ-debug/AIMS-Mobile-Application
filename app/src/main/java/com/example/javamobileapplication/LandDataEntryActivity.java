package com.example.javamobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Classes.JSONParser;


public class LandDataEntryActivity extends AppCompatActivity {

    //Declarations
    //Variables
    //Buttons
    Button btnPrevious; //previous button
    Button btnSubmit; //submit button
    private ProgressDialog pDialog;

    String land_id;
    String farmer_id;
    String selected_variety;
    Double amount;
    double converted_le;
    int variety_id;

    JSONObject json = new JSONObject();
    //make connection with remote database
    JSONParser jParser = new JSONParser();
    //Database
    DatabaseHelper db;
    private static String  url_add_cultivation = "http://ec2-54-210-97-143.compute-1.amazonaws.com/addCulti";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_data_entry);

        db = new DatabaseHelper(this); //instantiate database

        //seed data

        //Spinner- land measurements
        Spinner landMeasurements = (Spinner) findViewById(R.id.spinnerLand);

        //spinner - land measurements implementation
        ArrayAdapter<String> landMeasurementsArray = new ArrayAdapter<String>(
                LandDataEntryActivity.this,
                R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.land_measurements)
        ); //end of array adapter
        landMeasurementsArray.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        landMeasurements.setAdapter(landMeasurementsArray);

        //Spinner - Land Types
        Spinner landType = (Spinner) findViewById(R.id.spinnerLandType);
        ArrayList<String> land_types_list = db.getLandTypeList();

        //implement land type spinner
        ArrayAdapter<String> land_types_adapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                land_types_list
        );//end of adapter
        land_types_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        landType.setAdapter(land_types_adapter); //set adapter

        //Land Address Spinner
        final Spinner landAddress = (Spinner) findViewById(R.id.spinnerLandAddress);
        ArrayList<String> land_address_list = db.getLandAddressList();

        //implement adapter
        ArrayAdapter<String> land_address_adapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                land_address_list
        );//end of adapter
        land_address_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        landAddress.setAdapter(land_address_adapter);

        //Land extent value
        final EditText land_extent = (EditText) findViewById(R.id.editTextLandExtent);

        final String selected_measurement = landMeasurements.getSelectedItem().toString();

        String selected_land_type = landType.getSelectedItem().toString();

        //set data - dummy data

        selected_variety = getIntent().getStringExtra("crop_variety");
        farmer_id = getIntent().getStringExtra("fid");
        land_id = getIntent().getStringExtra("lid");
        variety_id = db.GetVarietyId(selected_variety);
        final int lt =  db.GetLandTypeId(selected_land_type);

        //Button impl - Previous
        btnPrevious = (Button) findViewById(R.id.buttonBack);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPreviousActivity();
            }
        });

        //Button impl - Submit

        btnSubmit = (Button) findViewById(R.id.buttonSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int land_id = landAddress.getSelectedItemPosition();
                String str_landExtent = land_extent.getText().toString();
                final double finalLe = parseDouble(str_landExtent);
                boolean result;

                EditText amountWhole = findViewById(R.id.crop);
                amount = parseDouble(amountWhole.getText().toString());

                //check for not selected
                if(land_id < 0){
                    Toast.makeText(getApplicationContext(), "Please select land address", Toast.LENGTH_LONG).show(); //show error message
                }
                else if(variety_id == 0){
                    Toast.makeText(getApplicationContext(), "Please enter crop variety details", Toast.LENGTH_LONG).show(); //show error message
                }
                else if(finalLe == 0){
                    Toast.makeText(getApplicationContext(), "Please enter land extent ", Toast.LENGTH_LONG).show(); //show error message
                }
                else if(lt == 0){
                    Toast.makeText(getApplicationContext(), "Please enter land type", Toast.LENGTH_LONG).show(); //show error message
                }
                else if(amount == 0){
                    Toast.makeText(getApplicationContext(), "Please enter amount", Toast.LENGTH_LONG).show(); //show error message
                }
                else{
                    //selected converter
                    if(selected_measurement == "Rood") {
                        converted_le = (float) (finalLe * 0.1012);
                    }
                    else if(selected_measurement == "Acre"){
                        converted_le = (float) (finalLe * 0.404686);
                    }
                    else if(selected_measurement == "Perch"){
                        converted_le = (float) (finalLe * 0.00252929);
                    }
                    else{
                        converted_le = finalLe;
                    }

                    //start ASynchronous activity
                    new AddCultivation().execute();

                }//end of nested if

            }
        });

    } //end of onCreate method

    class AddCultivation extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LandDataEntryActivity.this);
            pDialog.setMessage("Adding Data. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
            pDialog.dismiss();
        }


        @Override
        protected String doInBackground(String... strings) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("fid", farmer_id));
            params.add(new BasicNameValuePair("lid", land_id));
            params.add(new BasicNameValuePair("amount", amount.toString()));
            params.add(new BasicNameValuePair("extent", String.valueOf(converted_le)));
            params.add(new BasicNameValuePair("variety", String.valueOf(variety_id)));


            json = jParser.makeHttpRequest(url_add_cultivation,
                    "POST", params);

            Log.d("All Cultivation: ", json.toString());

            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            try {
                int success = json.getInt("success");
                String message = json.getString("message");
                if (success == 1) {
                    // successfully created product
                    Toast.makeText(LandDataEntryActivity.this, message , Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), DataEntrySuccessful.class);

                    startActivity(i);
                    // closing this screen
                    finish();
                } else {
                    Toast.makeText(LandDataEntryActivity.this, message , Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            pDialog.dismiss();
        }
    }

    //Method declaration
    public void openSuccessfulActivity(){
        Intent intent = new Intent(this, DataEntrySuccessful.class);
        startActivity(intent);
    }//end of method

    public void openPreviousActivity(){
        Intent intent = new Intent(this, DataEntryActivity.class);
        intent.putExtra("fid", getIntent().getStringExtra("fid"));
        intent.putExtra("lid", getIntent().getStringExtra("lid"));
        startActivity(intent);
    }//end of previous method

    public double parseDouble(String string){
        double result;
        if(string == null || string.isEmpty()){
            result = (float) 0.0;
            return result;
        }
        else{
            result = Double.parseDouble(string);
            return result;
        }
    }//end of method

} //end of activity class