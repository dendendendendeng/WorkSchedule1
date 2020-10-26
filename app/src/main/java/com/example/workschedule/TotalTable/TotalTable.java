package com.example.workschedule.TotalTable;

import com.example.workschedule.RoomInfomation.ItemInfo;
import com.example.workschedule.RoomInfomation.RoomWeekInfo;
import com.example.workschedule.StudentInfo.Student;
import com.example.workschedule.StudentInfo.StudentDay;

import java.util.ArrayList;

public class TotalTable {
    private ArrayList<ItemInfo> totalInfo = new ArrayList<>();

    private void setSpacePeople(ArrayList<Student> students){
        int sizei = totalInfo.size();
        for(int i = 0;i < sizei;i++){
            totalInfo.get(i).initSpacePoeple();
            int week = totalInfo.get(i).getWeek();
            int startTimeI = totalInfo.get(i).getStartHour() * 60 + totalInfo.get(i).getStartMin();
            int endTimeI = totalInfo.get(i).getEndHour() * 60 + totalInfo.get(i).getEndMin();
            int sizej = students.size();
            for(int j = 0;j < sizej;j++){
                StudentDay ADay = students.get(j).getDay(week);
                if(ADay.isSpace(startTimeI, endTimeI)){
                    totalInfo.get(i).setSpacePeople(totalInfo.get(i).getSpacePeople() + 1);
                    totalInfo.get(i).addSpaceName(students.get(j).getName());
                }
            }
        }
    }
    private void sort(){
        int size, Min, index;
        ArrayList<ItemInfo> newList = new ArrayList<>();
        while(totalInfo.size() != 0) {
            Min = 100;
            index = 0;
            size = totalInfo.size();
            for (int i = 0; i < size; i++) {
                if (totalInfo.get(i).getSpacePeople() < Min) {
                    index = i;
                    Min = totalInfo.get(i).getSpacePeople();
                }
            }
            newList.add(totalInfo.get(index));
            totalInfo.remove(index);
        }
        totalInfo = newList;
    }
    private Student searchStudent(ArrayList<Student> students, String name){
        int size = students.size();
        for(int i = 0;i <  size;i++){
            if(students.get(i).getName().equals(name)){
                return students.get(i);
            }
        }
        return null;
    }
    private void addStudent(Student student, int min){
        student.setHaveWorken(student.getHaveWorken() + min);
    }

    public void addRoom(RoomWeekInfo roomWeekInfo){
        ArrayList<ItemInfo> ARoom = roomWeekInfo.getAllInfo();
        int size = ARoom.size();
        for(int j = 0;j < size;j++){
            totalInfo.add(ARoom.get(j));
        }
    }
    public void distribution(ArrayList<Student> students, int averge){
        setSpacePeople(students);
        sort();
        int size = totalInfo.size();
        for(int i = 0;i < size; i++){
            setSpacePeople(students);
            if(totalInfo.get(i).getSpacePeople() <= totalInfo.get(i).getPeopleNumber()){
                ArrayList<String> people = totalInfo.get(i).getSpaceNames();
                int sizeT = people.size();
                for(int j = 0;j < sizeT;j++){
                    totalInfo.get(i).addStudentName(people.get(j));
                    Student student = searchStudent(students, people.get(j));
                    addStudent(student, totalInfo.get(i).getLastMin());
                    ItemInfo item = totalInfo.get(i);
                    student.addTime(item.getWeek(), item.getStartHour(), item.getStartMin(), item.getEndHour(), item.getEndMin());
                }
            }else{
                ArrayList<String> people = totalInfo.get(i).getSpaceNames();
                int sizeT = people.size();
                for(int j = 0;j < 100;j++){
                    int random = (int)(Math.random() * 10000) % size;
                    Student student = searchStudent(students, people.get(random));
                    int subMin = student.getHaveWorken() + totalInfo.get(i).getLastMin() - averge;
                    if(-60 >= subMin || subMin >= 60){
                        continue;
                    }else{
                        totalInfo.get(i).addStudentName(people.get(random));
                        addStudent(student, totalInfo.get(i).getLastMin());
                        ItemInfo item = totalInfo.get(i);
                        student.addTime(item.getWeek(), item.getStartHour(), item.getStartMin(), item.getEndHour(), item.getEndMin());
                        if(totalInfo.get(i).getPeopleNumber() == totalInfo.get(i).getStudentNames().size()){
                            break;
                        }
                    }
                }
            }
        }
    }
}
