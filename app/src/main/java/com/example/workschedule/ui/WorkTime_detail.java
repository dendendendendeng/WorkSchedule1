package com.example.workschedule.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.workschedule.R;
import com.example.workschedule.RoomInfomation.ItemInfo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class WorkTime_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_time_detail);
        final Button monday_bt=findViewById(R.id.monday_bt);
        monday_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dia;
                LayoutInflater lay=LayoutInflater.from(WorkTime_detail.this);
                View view1 = lay.inflate(R.layout.oneday_detail,null);
                AlertDialog.Builder bui=new AlertDialog.Builder(WorkTime_detail.this);
                bui.setView(view1);
                dia= bui.create();
                TextView textView=view1.findViewById(R.id.oneday_textView);
                textView.setText(monday_bt.getText());
                dia.show();
                Button add_worktime_item_bt=view1.findViewById(R.id.add_worktime_item_bt);
                add_worktime_item_bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog dia;
                        LayoutInflater lay=LayoutInflater.from(WorkTime_detail.this);
                        View view2 = lay.inflate(R.layout.oneday_detail_item,null);
                        AlertDialog.Builder bui=new AlertDialog.Builder(WorkTime_detail.this);
                        bui.setView(view2);
                        dia= bui.create();
                        dia.show();
                    }
                });
                //dia.dismiss();
            }
        });
    }
}

class Oneday_detail_item_Adapter extends ArrayAdapter<ItemInfo>{
    private  int resourceId;
    public Oneday_detail_item_Adapter(@NonNull Context context, int resource, @NonNull List<ItemInfo> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(this.getContext());
        View view = mInflater.inflate(this.resourceId, null);

        ItemInfo itemInfo =  this.getItem(position);
        return view;
    }
}
