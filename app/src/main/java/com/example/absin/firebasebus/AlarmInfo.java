package com.example.absin.firebasebus;

import android.app.AlarmManager;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

/**
 * Created by Junny_PC on 2019-05-29.
 */

public class AlarmInfo implements Parcelable {
    int requestCode1;
    int requestCode2;
    String days;
    Calendar startTime;
    Calendar endTime;
    String strStartTime;
    String strEndTime;
    int gapTime;
    String bus_routeId;
    String bus_number;
    String bus_stationId;
    String bus_station;

    private AlarmManager am;

    public AlarmInfo(int requestCode1, int requestCode2) {
        this.requestCode1 = requestCode1;
        this.requestCode2 = requestCode2;
    }
    //public AlarmInfo() {}

    protected AlarmInfo(Parcel parcel) {
        days = parcel.readString();
        strStartTime = parcel.readString();
        strEndTime = parcel.readString();
        gapTime = parcel.readInt();
        bus_routeId = parcel.readString();
        bus_number = parcel.readString();
        bus_stationId = parcel.readString();
        bus_station = parcel.readString();
    }

    public static final Creator<AlarmInfo> CREATOR = new Creator<AlarmInfo>() {
        @Override
        public AlarmInfo createFromParcel(Parcel parcel) {
            return new AlarmInfo(parcel);
        }

        @Override
        public AlarmInfo[] newArray(int size) {
            return new AlarmInfo[size];
        }
    };

    public int getRequestCode1() {
        return requestCode1;
    }

    public int getRequestCode2() {
        return requestCode2;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
        this.strStartTime = this.startTime.get(Calendar.HOUR_OF_DAY) + "시 " +
                this.startTime.get(Calendar.MINUTE) + "분";
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
        this.strEndTime = this.endTime.get(Calendar.HOUR_OF_DAY) + "시" +
                this.endTime.get(Calendar.MINUTE) + "분";
    }

    public String getStrStartTime() {
        return strStartTime;
    }

    public void setStrStartTime(String strStartTime) {
        this.strStartTime = strStartTime;
    }

    public String getStrEndTime() {
        return strEndTime;
    }

    public void setStrEndTime(String strEndTime) {
        this.strEndTime = strEndTime;
    }

    public int getGapTime() {
        return gapTime;
    }

    public void setGapTime(int gapTime) {
        this.gapTime = gapTime;
    }

    public String getBus_routeId() {
        return bus_routeId;
    }

    public void setBus_routeId(String bus_routeId) {
        this.bus_routeId = bus_routeId;
    }

    public String getBus_number() {
        return bus_number;
    }

    public void setBus_number(String bus_number) {
        this.bus_number = bus_number;
    }

    public String getBus_stationId() {
        return bus_stationId;
    }

    public void setBus_stationId(String bus_stationId) {
        this.bus_stationId = bus_stationId;
    }

    public String getBus_station() {
        return bus_station;
    }

    public void setBus_station(String bus_station) {
        this.bus_station = bus_station;
    }

    public AlarmManager getAm() {
        return am;
    }

    public void setAm(AlarmManager am) {
        this.am = am;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(days);
        parcel.writeString(strStartTime);
        parcel.writeString(strEndTime);
        parcel.writeInt(gapTime);
        parcel.writeString(bus_routeId);
        parcel.writeString(bus_number);
        parcel.writeString(bus_stationId);
        parcel.writeString(bus_station);
    }
}
