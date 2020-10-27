package com.example.javamobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Classes.JSONParser;

public class Approve_Data extends AppCompatActivity  {

    private ProgressDialog pDialog;
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> productsList;
    // url to get all products list
    private static String url_approval = "http://ec2-34-238-53-95.compute-1.amazonaws.com/getApproval";
    private static String url_approve = "http://ec2-34-238-53-95.compute-1.amazonaws.com/ApproveData";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_AID = "aid";

    JSONObject json;

    // products JSONArray
    JSONArray lands = null;

    private Button approve_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve__data);

        // Loading products in Background Thread
        new LoadApproval().execute();

        approve_btn = findViewById(R.id.approve_btn);
        approve_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Approve().execute();
            }
        });

    }

    class LoadApproval extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Approve_Data.this);
            pDialog.setMessage("Loading Approval. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String approval_id = getIntent().getStringExtra("aid");
            Log.d("hello", approval_id);
            params.add(new BasicNameValuePair("id", approval_id));
            // getting JSON string from URL
            json = jParser.makeHttpRequest(url_approval, "POST", params);

            // Check your log cat for JSON response
            Log.d(" Approval : ", json.toString());
            runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        // Checking for SUCCESS TAG
                        int success = json.getInt(TAG_SUCCESS);
                        String message = json.getString("message");
                        if (success == 1) {
                            // products found
                            // Getting Array of Products
                            lands = json.getJSONArray("approval");
                            // looping through All Products
                            JSONObject c = lands.getJSONObject(0);
                            // Storing each json item in variable
                            String id = c.getString("id");
                            String name = c.getString("firstName");
                            String variety_name = c.getString("name");
                            String addressNo = c.getString("addressNo");
                            String streetName = c.getString("streetName");
                            int cultivatedAmount = c.getInt("cultivatedLand");
                            String addressFull = addressNo + "," + streetName ;

                            TextView nameView = (TextView) findViewById(R.id.firstName);
                            TextView addressView = (TextView) findViewById(R.id.address);
                            TextView extentView = (TextView) findViewById(R.id.extent);
                            TextView varietyView = (TextView) findViewById(R.id.crop);

                            nameView.setText("Name : "+name);
                            addressView.setText("Address : "+addressFull);
                            extentView.setText("Variety : "+variety_name);
                            varietyView.setText("Extent : "+cultivatedAmount);


                        } else {
                            Intent i = new Intent(getApplicationContext(),
                                    MainActivity.class);
                            // Closing all previous activities
                            startActivity(i);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            return null;
        }
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), Approval_List.class);
        startActivity(i);
    }

    class Approve extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Approve_Data.this);
            pDialog.setMessage("Approving Data. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected String doInBackground(String... strings) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String approval_id = getIntent().getStringExtra("aid");
            params.add(new BasicNameValuePair("id", approval_id));
            // getting JSON string from URL
            json = jParser.makeHttpRequest(url_approve, "POST", params);
            return null;
        }

        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            Toast.makeText(Approve_Data.this, "The data has been approved please go back to approve other data", Toast.LENGTH_SHORT).show();

        }
    }
}