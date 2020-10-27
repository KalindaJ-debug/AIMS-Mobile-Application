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


public class Farmer_Land extends ListActivity {

    private ProgressDialog pDialog;
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> productsList;
    // url to get all products list
    private static String url_all_lands = "http://ec2-34-238-53-95.compute-1.amazonaws.com/getLand";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_LANDS = "land";
    private static final String TAG_LID = "lid";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_FID = "fid";
    private static final String TAG_LANE = "streetName";
    private static final String TAG_TOWN = "town";

    // products JSONArray
    JSONArray lands = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer__land);;
        // Hashmap for ListView
        productsList = new ArrayList<HashMap<String, String>>();

        // Loading products in Background Thread
        new LoadAllLand().execute();
        // Get listview
        ListView lv = getListView();

        // on seleting single product
        // launching Edit Product Screen
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String lid = ((TextView) view.findViewById(R.id.lid)).getText()
                        .toString();
                String fid = getIntent().getStringExtra("id");
                // Starting new intent
                Intent in = new Intent(getApplicationContext(),
                        DataEntryActivity.class);
                // sending pid to next activity
                in.putExtra(TAG_LID, lid);
                in.putExtra(TAG_FID, fid);
                // starting new activity and expecting some response back
                startActivityForResult(in, 100);
            }
        });
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

    class LoadAllLand extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Farmer_Land.this);
            pDialog.setMessage("Loading products. Please wait...");
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
            params.add(new BasicNameValuePair("username", getIntent().getStringExtra("id")));
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_lands, "POST", params);
            // Check your log cat for JSON response
            Log.d("All Land : ", json.toString());
            try {
                // Checking for SUCCESS TAG
                int success = 1;
                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    lands = json.getJSONArray(TAG_LANDS);
                    // looping through All Products
                    for (int i = 0; i < lands.length(); i++) {
                        JSONObject c = lands.getJSONObject(i);
                        // Storing each json item in variable
                        String id = c.getString("id");
                        String streetName = c.getString("streetName");
                        String laneName = c.getString("laneName");
                        String town = c.getString("town");
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
                        // adding each child node to HashMap key => value
                        map.put(TAG_LID, id);
                        map.put(TAG_ADDRESS, streetName);
                        map.put(TAG_LANE, laneName);
                        map.put(TAG_TOWN, town);
                        // adding HashList to ArrayList
                        productsList.add(map);
                    }
                } else {
                    // no products found
                    // Launch Add New product Activity
                    Intent i = new Intent(getApplicationContext(),
                            MainActivity.class);
                    // Closing all previous activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
            runOnUiThread(new Runnable() {
                public void run() {
                    ListAdapter adapter = new SimpleAdapter(
                            Farmer_Land.this, productsList,
                            R.layout.list_item, new String[] { TAG_LID,
                            TAG_ADDRESS, TAG_LANE, TAG_TOWN},
                            new int[] { R.id.lid, R.id.address, R.id.lane, R.id.town });
                    setListAdapter(adapter);
                }
            });
        }
    }

}