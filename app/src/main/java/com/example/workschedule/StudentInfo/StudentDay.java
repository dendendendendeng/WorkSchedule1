package com.example.workschedule.StudentInfo;

import java.util.ArrayList;

public class StudentDay {
    private int Week;
    private ArrayList<StudentTime> classTime = new ArrayList<>();

    public StudentDay(int Week){
        this.Week = Week;
    }

    public void addTime(int startHour,int startMin, int endHour, int endMin){
        int i = -1;
        int size = classTime.size();
        if(size == 0){
            i = 0;
        }
        int startTime = startHour * 60 + startMin;
        int endTime = endHour * 60 + endMin;
        for(int j = 0;j < size;j++){
            int startTimeTarget = classTime.get(j).getStartHour() * 60 + classTime.get(j).getStartMin();
            int endTimeTarget = classTime.get(j).getEndHour() * 60 + classTime.get(j).getEndMin();
            if(endTime <= startTimeTarget){
                i = j;
                break;
            }else if(endTime <= endTimeTarget){
                break;
            }else if(endTime >= endTimeTarget){
                if(startTime >= endTimeTarget){
                    if(j == size - 1){
                        i = j + 1;
                    }
                    continue;
                }else{
                    break;
                }
            }
        }

        if(i == -1){
            throw new SecurityException("新加入的时间与原来的时间冲突");
        }

        StudentTime item = new StudentTime();
        item.setStartTime(startHour, startMin);
        item.setEndTime(endHour, endMin);
        classTime.add(i, item);
    }

    public boolean isSpace(int startTime, int endTime){
        int size = classTime.size();
        for(int i = 0;i < size; i++){
            int startTimeTarget = classTime.get(i).getStartHour() * 60 + classTime.get(i).getStartMin();
            int endTimeTarget = classTime.get(i).getEndHour() * 60 + classTime.get(i).getEndMin();
            if((startTimeTarget <= startTime && startTime <= endTimeTarget) ||
                    (startTimeTarget <= endTime && endTime <= endTimeTarget) ||
                    (startTimeTarget <= startTime && endTime <= endTimeTarget)){
                return false;
            }
        }

        return true;
    }

    public ArrayList<StudentTime> getClassTime() {
        return classTime;
    }
}
