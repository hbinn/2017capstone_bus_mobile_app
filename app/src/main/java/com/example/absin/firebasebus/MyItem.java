package com.example.absin.firebasebus;

/**
 * Created by Junny_PC on 2019-05-24.
 */

public class MyItem {
    private int bus;
    private int onStop;
    private int offStop;
    private String day;
    private int time;
    private int gapTime;

    public int getBus() {
        return bus;
    }

    public void setBus(int bus) {
        this.bus = bus;
    }

    public int getonStop() {
        return onStop;
    }

    public void setonStop(int onStop) {
        this.onStop = onStop;
    }

    public int getOffStop() {
        return offStop;
    }

    public void setOffStop(int offStop) {
        this.offStop = offStop;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getGapTime() {
        return gapTime;
    }

    public void setGapTime(int gapTime) {
        this.gapTime = gapTime;
    }
}