package com.example.workschedule.RoomInfomation;

import java.util.ArrayList;

public class ItemInfo {
    private int startHour,startMin;
    private int endHour, endMin;
    private ArrayList<String> StudentNames = new ArrayList<>();
    private int PeopleNumber;
    private int Week;
    private int spacePeople = 0;
    private ArrayList<String> spaceNames = new ArrayList<>();

    public void initSpacePoeple(){
        spacePeople = 0;
        spaceNames = new ArrayList<>();
    }

    public  ItemInfo(int Week, int People){
        this.Week = Week;
        this.PeopleNumber = People;
    }

    public ItemInfo(ItemInfo itemInfo){
        this.startHour = itemInfo.getStartHour();
        this.startMin = itemInfo.getStartMin();
        this.endHour = itemInfo.getEndHour();
        this.endMin = itemInfo.getEndMin();
        this.StudentNames = itemInfo.getStudentNames();
        this.PeopleNumber = itemInfo.getPeopleNumber();
        this.Week = itemInfo.getWeek();
        this.spacePeople = itemInfo.getSpacePeople();
        this.spaceNames = itemInfo.getSpaceNames();
    }

    public void setStratTime(int hour, int minute){
        this.startHour = hour;
        this.startMin = minute;
    }

    public void setEndTime(int hour, int minute){
        this.endHour = hour;
        this.endMin = minute;
    }

    public void addStudentName(String studentName){
        this.StudentNames.add(studentName);
    }

    public int getStartHour() {
        return startHour;
    }

    public int getStartMin() {
        return startMin;
    }

    public int getEndHour() {
        return endHour;
    }

    public int getEndMin() {
        return endMin;
    }

    public ArrayList<String> getStudentNames() {
        return StudentNames;
    }

    public int getWeek(){
        return Week;
    }

    public int getLastMin(){
        int end = endHour * 60 +endMin;
        int start = startHour * 60 + startMin;
        return (end - start) * PeopleNumber;
    }

    public int getSpacePeople() {
        return spacePeople;
    }

    public void setSpacePeople(int spacePeople) {
        this.spacePeople = spacePeople;
    }

    public void addSpaceName(String name){
        this.spaceNames.add(name);
    }

    public int getPeopleNumber() {
        return PeopleNumber;
    }

    public void setPeopleNumber(int peopleNumber) {
        PeopleNumber = peopleNumber;
    }

    public ArrayList<String> getSpaceNames() {
        return spaceNames;
    }

    public void setSpaceNames(ArrayList<String> spaceNames) {
        this.spaceNames = spaceNames;
    }
}
