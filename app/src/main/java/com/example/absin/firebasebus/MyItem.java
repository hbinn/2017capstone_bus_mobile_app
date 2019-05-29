package com.example.absin.firebasebus;

import java.util.Calendar;

/**
 * Created by Junny_PC on 2019-05-24.
 */

public class MyItem {
    //private boolean[] week = new boolean[8];
    private String days;
    private String startTime;
    private String endTime;
    private int gapTime;
    private String bus_number;
    private String bus_station;

//    public boolean[] getWeek() {
//        return week;
//    }
//
//    public void setWeek(boolean[] week) {
//        this.week = week;
//    }

    public String getDays() { return days; }

    public void setDays(String days) { this.days = days; }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getGapTime() {
        return gapTime;
    }

    public void setGapTime(int gapTime) {
        this.gapTime = gapTime;
    }

    public String getBus_number() {
        return bus_number;
    }

    public void setBus_number(String bus_number) {
        this.bus_number = bus_number;
    }

    public String getBus_station() {
        return bus_station;
    }

    public void setBus_station(String bus_station) {
        this.bus_station = bus_station;
    }
}