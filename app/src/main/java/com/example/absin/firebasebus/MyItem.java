package com.example.absin.firebasebus;

import java.util.Calendar;

/**
 * Created by Junny_PC on 2019-05-24.
 */

public class MyItem {
    //private boolean[] week = new boolean[8];
    private String REQCODE1;
    private String REQCODE2;
    private String days;
    private String startTime;
    private String endTime;
    private String gapTime;
    private String bus_number;
    private String bus_station;

//    public boolean[] getWeek() {
//        return week;
//    }
//
//    public void setWeek(boolean[] week) {
//        this.week = week;
//    }


    public String getREQCODE1() {
        return REQCODE1;
    }

    public void setREQCODE1(String REQCODE1) {
        this.REQCODE1 = REQCODE1;
    }

    public String getREQCODE2() {
        return REQCODE2;
    }

    public void setREQCODE2(String REQCODE2) {
        this.REQCODE2 = REQCODE2;
    }

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

    public String getGapTime() {
        return gapTime;
    }

    public void setGapTime(String gapTime) {
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

    public String toString() {
        String str =  REQCODE1 + "," + REQCODE2 + "," + days + "," +
                startTime + "," + endTime + "," + gapTime + "," +
                bus_number + "," + bus_station;

        return str;
    }
}