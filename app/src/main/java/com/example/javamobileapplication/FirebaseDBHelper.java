package com.example.javamobileapplication;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDBHelper {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private List<Approval> approvalList = new ArrayList<>();
    String TAG = "MyApp";

    public FirebaseDBHelper() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Approval");
    }

    public void areadApproval() {

    }

    public interface DataStatus {
        void DataIsLoaded(List<Approval> approvals, List<String> keys);
        void DataInserted();
        void DataUpdated();
        void DataDeleted();
    }

    public void readApproval(final DataStatus dataStatus) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                approvalList.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Approval approval =  keyNode.getValue(Approval.class);
                    Log.d(TAG, keyNode.getValue(Approval.class).toString());
                    approvalList.add(approval);
                }
                dataStatus.DataIsLoaded(approvalList, keys);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public void submitApproval(Approval approval, final DataStatus dataStatus) {
        String key = myRef.push().getKey();
        myRef.child(key).setValue(approval)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataInserted();
                    }
                });
    }

    public void updateApproval(String key, Approval approval, final DataStatus dataStatus) {
        myRef.child(key).setValue(approval)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataUpdated();
                    }
                });
    }

    public void deleteApproval(String key, final DataStatus dataStatus) {
        myRef.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataDeleted();
                    }
                });
    }

    public void submitData() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Approval approval = new Approval();

        approval.setFirstName("Nelaka");
        approval.setLastName("Jayasinghe");
        approval.setProvince("Western");
        approval.setDistrict("Nawala");
        approval.setRegion("Region 1");
        approval.setSeason("Maha");
        approval.setSubmitDate("10/01/2020");
        approval.setCultivatedAmount((float) 23.567);
        approval.setHarvestAmount((float) 3.567);
        approval.setStatus(0);
        approval.setReason("Test");

        reference.child("Approval").push().setValue(approval);
        Log.d(TAG, "Submitted Data");
    }
}
