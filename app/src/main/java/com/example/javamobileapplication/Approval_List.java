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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Classes.JSONParser;

public class Approval_List extends ListActivity {

    private ProgressDialog pDialog;
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> productsList;
    // url to get all products list
    private static String url_all_lands = "http://192.168.1.8:8000/getAllApproval";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_AID = "aid";
    private static final String TAG_APPROVAL = "approval";
    private static final String TAG_LAND = "land_id";
    private static final String TAG_VARIETY = "variety_id";
    private static final String TAG_SEASON = "season";
    private static final String TAG_HARVEST = "harvestedAmount";
    private static final String TAG_CULTLAND = "cultivatedLand";


    // products JSONArray
    JSONArray lands = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval__list);;
        // Hashmap for ListView
        productsList = new ArrayList<HashMap<String, String>>();

        // Loading products in Background Thread
        new LoadAllApproval().execute();
        // Get listview
        ListView lv = getListView();

        // on selecting single product
        // launching Edit Product Screen
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String aid = ((TextView) view.findViewById(R.id.lid)).getText()
                        .toString();
                String fid = getIntent().getStringExtra("id");
                // Starting new intent
                Intent in = new Intent(getApplicationContext(),
                        Approve_Data.class);
                // sending pid to next activity
                in.putExtra(TAG_AID, aid);
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

    class LoadAllApproval extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Approval_List.this);
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
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_lands, "GET", params);
            // Check your log cat for JSON response
            Log.d("All Approval : ", json.toString());
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
                String message = json.getString("message");
                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    lands = json.getJSONArray("approval");
                    // looping through All Products
                    for (int i = 0; i < lands.length(); i++) {
                        JSONObject c = lands.getJSONObject(i);
                        // Storing each json item in variable
                        String id = c.getString("id");
                        String land_id = c.getString("land_id");
                        String variety_id = c.getString("variety_id");
                        String season = c.getString("season");
                        String harvestedAmount = c.getString("harvestedAmount");
                        String cultivatedAmount = c.getString("cultivatedLand");

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
                        // adding each child node to HashMap key => value
                        map.put(TAG_AID, id);
                        map.put(TAG_LAND, land_id);
                        map.put(TAG_VARIETY, variety_id);
                        map.put(TAG_SEASON, season);
                        map.put(TAG_HARVEST, harvestedAmount);
                        map.put(TAG_CULTLAND, cultivatedAmount);

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
                            Approval_List.this, productsList,
                            R.layout.list_approval, new String[] { TAG_AID,
                            TAG_LAND, TAG_VARIETY, TAG_SEASON, TAG_HARVEST, TAG_CULTLAND },
                            new int[] { R.id.lid, R.id.land, R.id.variety, R.id.season, R.id.harvest, R.id.cult });
                    setListAdapter(adapter);
                }
            });
        }
    }
}