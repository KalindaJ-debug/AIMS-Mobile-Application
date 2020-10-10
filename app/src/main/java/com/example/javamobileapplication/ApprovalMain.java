package com.example.javamobileapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ApprovalMain extends AppCompatActivity {

    ApprovalDBHelper dbHelper = new ApprovalDBHelper();
    String TAG = "MyApp";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_main);

        dbHelper.setTest("TestValue 1");
        dbHelper.setTestColumn1("Tests Value 2");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        String approvalId = reference.push().getKey();
        //Approval approval = new Approval("Nelaka", "Jayasinghe", "Western", "Nawala", "Region 1", "Maha", "10/01/2020", (float) 23.567, (float) 35.6, 1, "Test");
        //reference.child("test").push().setValue(dbHelper);
        Log.d(TAG, "Test");

//        FirebaseDBHelper firebaseDBHelper = new FirebaseDBHelper();
//        firebaseDBHelper.submitData();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Approval");

        recyclerView = (RecyclerView) findViewById(R.id.approvalList);
        new FirebaseDBHelper().readApproval(new FirebaseDBHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Approval> approvals, List<String> keys) {
                new RecycleView_Config().setConfig(recyclerView, ApprovalMain.this, approvals, keys);
            }

            @Override
            public void DataInserted() {

            }

            @Override
            public void DataUpdated() {

            }

            @Override
            public void DataDeleted() {

            }
        });
    }

    public void proceedActivity(View view) {
        Intent intent = new Intent(this, ApprovalDescription.class);
        intent.putExtra("farmerFirst", "Nelaka");
        intent.putExtra("farmerLast", "Jayasinghe");
        intent.putExtra("district", "Nawala");
        intent.putExtra("region", "Region 1");
        intent.putExtra("submittedDate", "06/09/2020");
        intent.putExtra("harvest", "45.897");
        startActivity(intent);
    }

    public void descriptionActivity() {
        Intent intent = new Intent(this, ApprovalDescription.class);
        intent.putExtra("farmerFirst", "Nelaka");
        intent.putExtra("farmerLast", "Jayasinghe");
        intent.putExtra("district", "Nawala");
        intent.putExtra("region", "Region 1");
        intent.putExtra("submittedDate", "06/09/2020");
        intent.putExtra("harvest", "45.897");
        startActivity(intent);
    }
}