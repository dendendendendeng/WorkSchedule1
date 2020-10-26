package com.example.workschedule.StudentInfo;

import java.util.ArrayList;

public class Student {
    private int studentID;
    private String name;
    private ArrayList<StudentDay> StudentWeeek = new ArrayList<>();
    private int haveWorken = 0;

    public Student(){
        for(int i = 0;i < 7;i++){
            StudentWeeek.add(new StudentDay(i));
        }
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTime(int Week, int startHour, int startMin, int endHour, int endMin){
        if(Week < 0 || Week > 6){
            throw new SecurityException("请输入正确的时间");
        }
        StudentWeeek.get(Week).addTime(startHour, startMin, endHour, endMin);
    }

    public StudentDay getDay(int Week){
        return StudentWeeek.get(Week);
    }

    public int getHaveWorken() {
        return haveWorken;
    }

    public void setHaveWorken(int haveWorken) {
        this.haveWorken = haveWorken;
    }
}
