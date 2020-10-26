package com.example.workschedule.ui;

import androidx.appcompat.app.AppCompatActivity;
import com.example.workschedule.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Add_WorkTime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__work_time);

        Button add_worktime_bt=findViewById(R.id.add_worktime_bt);
        add_worktime_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Add_WorkTime.this,WorkTime_detail.class);
                startActivity(intent);
            }
        });
    }
}
