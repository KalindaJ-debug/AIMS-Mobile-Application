package com.example.javamobileapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecycleView_Config extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view__config);
    }

    private Context mContext;
    private RecycleView_Config.ApprovalsAdapter approvalsAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Approval> approvals, List<String> keys) {
        mContext = context;
        approvalsAdapter = new RecycleView_Config.ApprovalsAdapter(approvals, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(approvalsAdapter);
    }

    class ApprovalItemView extends RecyclerView.ViewHolder
    {
        private TextView farmerFirstName;
        private TextView farmerLastName;
        private TextView province;
        private TextView district;
        private TextView region;
        private TextView status;
        private TextView submittedDate;
        private float cultivatedAmount, harvestAmount;

        private String key, season;

        public ApprovalItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.approval_list_item, parent, false));

            farmerFirstName = (TextView) itemView.findViewById(R.id.txtFarmerFirstName);
            farmerLastName = (TextView) itemView.findViewById(R.id.txtFarmerLastName);
            province = (TextView) itemView.findViewById(R.id.txtProvince);
            district = (TextView) itemView.findViewById(R.id.txtDistrict);
            region = (TextView) itemView.findViewById(R.id.txtSeason);
            status = (TextView) itemView.findViewById(R.id.txtStatusUpdate);
            submittedDate = (TextView) itemView.findViewById(R.id.txtSumittedDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ApprovalDescription.class);
                    intent.putExtra("farmerFirst", farmerFirstName.getText().toString());
                    intent.putExtra("farmerLast", farmerLastName.getText().toString());
                    intent.putExtra("province", province.getText().toString());
                    intent.putExtra("district", district.getText().toString());
                    intent.putExtra("region", region.getText().toString());
                    intent.putExtra("submittedDate", submittedDate.getText().toString());
                    intent.putExtra("harvestAmount", harvestAmount);
                    intent.putExtra("cultivatedAmount", cultivatedAmount);
                    intent.putExtra("season", season);
                    intent.putExtra("key", key);
                    mContext.startActivity(intent);
                }
            });
        }

        public void bind(Approval approval, String key) {
            farmerFirstName.setText(approval.getFirstName());
            farmerLastName.setText(approval.getLastName());
            province.setText(approval.getProvince());
            district.setText(approval.getDistrict());
            region.setText(approval.getRegion());
            if (approval.getStatus() == 0) {
                status.setText("Pending");
                status.setTextColor(Color.BLUE);
            } else if (approval.getStatus() == 1) {
                status.setText("Accepted");
                status.setTextColor(Color.GREEN);
            } else {
                status.setText("Denied");
                status.setTextColor(Color.RED);
            }
            submittedDate.setText(approval.getSubmitDate());
            this.key = key;
            this.cultivatedAmount = approval.getCultivatedAmount();
            this.harvestAmount = approval.getHarvestAmount();
            this.season = approval.getSeason();
        }
    }

    class ApprovalsAdapter extends RecyclerView.Adapter<RecycleView_Config.ApprovalItemView>
    {
        private List<Approval> approvalList;
        private List<String> keys;

        public ApprovalsAdapter(List<Approval> approvalList, List<String> keyList) {
            this.approvalList = approvalList;
            this.keys = keyList;
        }

        @NonNull
        @Override
        public RecycleView_Config.ApprovalItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecycleView_Config.ApprovalItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecycleView_Config.ApprovalItemView holder, int position) {
            holder.bind(approvalList.get(position), keys.get(position));
        }

        @Override
        public int getItemCount() {
            return approvalList.size();
        }
    }
}