package com.example.workschedule;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.workschedule.StudentInfo.Student;
import com.example.workschedule.StudentInfo.StudentDay;
import com.example.workschedule.StudentInfo.StudentTime;
import com.example.workschedule.ui.functional_zone;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.leon.lfilepickerlibrary.LFilePicker;
import com.leon.lfilepickerlibrary.utils.Constant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

//import jxl.Workbook;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_CHOOSEFILE =901 ;
    private int mIconType;
    private SqliteHelper helper = new SqliteHelper(this,"work_info.db3",null,1);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab=findViewById(R.id.start);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, functional_zone.class);
                startActivity(intent);
            }
        });

        //开启数据库进行数据读入，数据的名字是work_info.db3
        SQLiteDatabase database = helper.getReadableDatabase();
        database.beginTransaction();//开启事务

        database.endTransaction();//关闭事务
        helper.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //开启数据库进行数据写回
        SQLiteDatabase database = helper.getWritableDatabase();
        database.beginTransaction();//开启事务，可以提高效率

        database.endTransaction();//关闭事务
        helper.close();
    }


}


