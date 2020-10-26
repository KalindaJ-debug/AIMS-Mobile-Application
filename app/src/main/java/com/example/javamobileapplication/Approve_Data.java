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
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Classes.JSONParser;

public class Approve_Data extends ListActivity {

    private ProgressDialog pDialog;
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> productsList;
    // url to get all products list
    private static String url_approval = "http://192.168.1.8:8000/getApproval";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_AID = "aid";


    // products JSONArray
    JSONArray lands = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval__list);;
        // Hashmap for ListView
        productsList = new ArrayList<HashMap<String, String>>();

        // Loading products in Background Thread
        new LoadApproval().execute();

    }
    // Response from Edit Product Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if result code 100
        if (resultCode == 100) {
            // if result code 100 is received
            // means user edited/deleted product
            // reload this screen again
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
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
            JSONObject json = jParser.makeHttpRequest(url_approval, "POST", params);

            // Check your log cat for JSON response
            Log.d(" Approval : ", json.toString());
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
                        String name = json.getJSONArray("name").toString();
                        String address = json.getJSONArray("address").toString();
                        String variety_name = json.getJSONArray("variety").toString();
                        String cultivatedAmount = c.getString("cultivatedLand");

                        TextView nameView = findViewById(R.id.name);
                        TextView addressView = findViewById(R.id.address);
                        TextView extentView = findViewById(R.id.extent);
                        TextView varietyView = findViewById(R.id.name);

                        nameView.setText("Name : "+name);
                        addressView.setText("Address : "+address);
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
            return null;
        }
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();

        }
    }
}