package com.example.workschedule.RoomInfomation;

import java.util.ArrayList;

public class RoomWeekInfo {
    private String RoomName;
    private int totalMinute = 0;
    private ArrayList<ItemInfo> totalInfo = new ArrayList<>();

    public RoomWeekInfo(String RoomName){
        this.RoomName = RoomName;
    }

    public void addTime(int Week, int startHour,int startMin, int endHour, int endMin, int PeopleNumber){
        int i = -1;
        int size = totalInfo.size();
        int startTime = startHour * 60 + startMin;
        int endTime = endHour * 60 + endMin;
        if(size == 0){
            i = 0;
        }
        for(int j = 0;j < size;j++){
            if(Week > totalInfo.get(j).getWeek()){
                i = j;
                break;
            }else{
                int startTimeTarget = totalInfo.get(j).getStartHour() * 60 + totalInfo.get(j).getStartMin();
                int endTimeTarget = totalInfo.get(j).getEndHour() * 60 + totalInfo.get(j).getEndMin();
                if((startTime >= startTimeTarget && startTime <= endTimeTarget) ||
                        (endTime >= startTimeTarget && endTime <= endTimeTarget) ||
                        (startTime >= startTimeTarget && endTime <= endTimeTarget)){
                    break;
                }else if(endTime <= startTimeTarget){
                    i = j;
                    break;
                }else{
                    if(j == size - 1){
                        i = size;
                    }
                }
            }
        }

        if(i == -1){
            throw new SecurityException("新加入的时间与原来的时间冲突");
        }

        ItemInfo item = new ItemInfo(Week, PeopleNumber);
        item.setStratTime(startHour, startMin);
        item.setEndTime(endHour, endMin);
        totalInfo.add(i, item);

        totalMinute += item.getLastMin();
    }

    public ArrayList<ItemInfo> getTime(int Week){
        ArrayList<ItemInfo> returnInfo = new ArrayList<>();
        int size = totalInfo.size();
        for(int j = 0;j < size;j++){
            if(Week == totalInfo.get(j).getWeek()){
                returnInfo.add(new ItemInfo(totalInfo.get(j)));
            }
        }
        return returnInfo;
    }

    public String getRoomName(){
        return RoomName;
    }

    public ArrayList<ItemInfo> getAllInfo(){
        return totalInfo;
    }

    private ItemInfo searchDay(int Week, int position){
        int size = totalInfo.size();
        int index = 0;
        for(int j = 0;j < size;j++){
            if(totalInfo.get(j).getWeek() == Week){
                index = j;
            }
        }
        if(index + position >= size){
            throw new SecurityException("超出索引界限了。");
        }
        return totalInfo.get(index + position);
    }

    public void changeInfo(int Week, int position, int newStartHour, int newStartMin, int newEndHour, int newEndMin){
        ItemInfo itemInfo = searchDay(Week, position);
        itemInfo.setStratTime(newStartHour, newStartMin);
        itemInfo.setEndTime(newEndHour, newEndMin);
    }

    public void deleteInfo(int Week, int position){
        ItemInfo itemInfo = searchDay(Week, position);
        totalInfo.remove(itemInfo);
    }
}