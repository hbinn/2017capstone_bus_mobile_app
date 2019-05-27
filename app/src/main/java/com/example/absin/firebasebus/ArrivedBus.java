package com.example.absin.firebasebus;

/**
 * Created by absin on 2019-05-22.
 */

public class ArrivedBus {
        String predictTime1;
        String predictTime2;
        String routeId; // 노선아이디 aka 버스 번호
        String stationId;

        public String getPredictTime1() { return predictTime1; }
        public String getPredictTime2() { return predictTime2; }
        public String getRouteId() { return routeId; }
        public String getStationId() { return stationId;}

        public void setPredictTime1(String predictTime1) { this.predictTime1 = predictTime1; }
        public void setPredictTime2(String predictTime2) { this.predictTime2 = predictTime2; }
        public void setRouteId(String routeId) { this.routeId = routeId; }
        public void setStationId(String stationId) { this.stationId = stationId; }
}
