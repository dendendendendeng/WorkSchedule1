package com.example.workschedule.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.workschedule.Consant;
import com.example.workschedule.MainActivity;
import com.example.workschedule.R;
import com.example.workschedule.StudentInfo.Student;
import com.example.workschedule.StudentInfo.StudentDay;
import com.example.workschedule.StudentInfo.StudentTime;
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

public class functional_zone extends AppCompatActivity {
    public ArrayList<Student> AllStudents=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functional_zone);
        Button readxml=findViewById(R.id.readxml);
        readxml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(functional_zone.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(functional_zone.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }
                new LFilePicker()
                        .withActivity(functional_zone.this)
                        .withRequestCode(Consant.REQUESTCODE_FROM_ACTIVITY)
                        .withTitle("文件选择")
                        // .withIconStyle(mIconType)
                        .withMutilyMode(true)//多选开关
                        .withMaxNum(30)
                        .withStartPath("/storage/emulated/0/StuCourseExecl")//指定初始显示路径
                        .withNotFoundBooks("至少选择一个文件")
                        .withIsGreater(false)//过滤文件大小 小于指定大小的文件
                        .withFileSize(500 * 1024)//指定文件大小为500K
                        .withChooseMode(true)//false:文件夹选择模式,true:文件选择模式
                        .withFileFilter(new String[]{"xls"})
                        .start();
            }
        });
        Button addworktime_bt=findViewById(R.id.addworktime_bt);
        addworktime_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(functional_zone.this,Add_WorkTime.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Consant.REQUESTCODE_FROM_ACTIVITY) {
                List<String> list = data.getStringArrayListExtra(Constant.RESULT_INFO);
                String path = data.getStringExtra("path");
                // Toast.makeText(getApplicationContext(), "选中的路径为" + path, Toast.LENGTH_SHORT).show();
                initStudentInfo(list);
                Log.i("LeonFilePicker", path);
            }
        }
    }

    public void initStudentInfo(List<String> list){//从本地导入的课表初始化到类中

        try {
            for(int i=0;i<list.size();i++){
                Student onestudent=new Student();
                //InputStream inputStream=new FileInputStream(list.get(i));
                Workbook book = Workbook
                        .getWorkbook(new File(list.get(i)));
                //Workbook book=Workbook.getWorkbook(inputStream);
                book.getNumberOfSheets();
                //获取第一个工作表
                Sheet sheet=book.getSheet(0);
                //获取行列数
                int Rows=sheet.getRows();
                int Cols=sheet.getColumns();
                Cell cell=sheet.getCell(0,0);//包含名字的字符串
                String s=cell.getContents();
                for(int j=0;j<s.length();j++)
                    if(s.substring(j,j+1).equals("2")){
                        onestudent.setName(s.substring(0,j));//获得姓名
                        break;
                    }
                for(int day=0;day<7;day++){
                    for(int cols=0;cols<14;cols++){
                        Cell cell1=sheet.getCell(cols+1,day+3);
                        if(!cell1.getContents().equals("")){
                            int starthours=Integer.parseInt(sheet.getCell(cols+1,2).getContents().substring(0,2));
                            int startmins=Integer.parseInt(sheet.getCell(cols+1,2).getContents().substring(3,5));
                            while (!sheet.getCell(cols+1,day+3).getContents().equals("")&&cols<14){
                                cols++;
                            }
                            int endhours=0,endmins=0;
                            if(cols==14){
                                endhours=22;
                                endmins=30;
                            }
                            else {
                                endhours = Integer.parseInt(sheet.getCell(cols, 2).getContents().substring(6, 8));
                                endmins = Integer.parseInt(sheet.getCell(cols, 2).getContents().substring(9));
                            }
                            int week=(day+1)%7;
                            Log.d("123",onestudent.getName()+"星期"+week+endhours+":"+endmins);
                            onestudent.addTime(week,starthours,startmins,endhours,endmins);
                        }
                    }
                }
                AllStudents.add(onestudent);
            }
            StudentDay studentDay = AllStudents.get(0).getDay(1);
            ArrayList<StudentTime> times = studentDay.getClassTime();
            Toast.makeText(getApplicationContext(), "选中的路径为" + times.get(0).getStartHour()+":"+times.get(0).getStartMin(), Toast.LENGTH_SHORT).show();
        } catch (IOException | BiffException e) {
            e.printStackTrace();
        }
    }
}
