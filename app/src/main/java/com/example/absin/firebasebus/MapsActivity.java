package com.example.absin.firebasebus;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        drawMap();

//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));
    }

    void drawMap() {

        //Manifest에서의 권한을 먼저 체크
        int check = ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if(check != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }else {
            mMap.setMyLocationEnabled(true); //내 위치 표시
            mMap.getUiSettings().setZoomControlsEnabled(true);  //줌 컨트롤이 되는지
            mMap.setTrafficEnabled(true); //길 막힘 표시가 된다고 하는데 잘 모르겠음....
            mMap.setBuildingsEnabled(true);//지도에서 빌딩 표시 (이거 없어도 표기 되던데
            mMap.setIndoorEnabled(true); // 건물 안에서 지도 사용
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Criteria criteria = new Criteria();

            String provider = locationManager.getBestProvider(criteria, true);
            Location location = locationManager.getLastKnownLocation(provider);

            double latitude;
            double longtitude;

            if(location != null) {
                latitude = location.getLatitude();
                longtitude = location.getLongitude();
            }else {
                latitude = 37.5796212;
                longtitude = 126.9748523; //만약에 현재 위치 못 잡아오면 경복궁 나오게 해둠
            }

            LatLng geoPoint = new LatLng(latitude, longtitude); //위치 표시, 위치를 좀 못 잡는데 이건 내 핸드폰의 gps 문제 같음
            mMap.moveCamera(CameraUpdateFactory.newLatLng(geoPoint)); //지도 표시할때 멋진 카메라 무빙
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(geoPoint, 11)); //멋진 카메라 무빙을 가능하게 하는 애니메이션

//            MarkerOptions marker = new MarkerOptions();
//            marker.position(geoPoint);
//            marker.title("경복궁");
//            marker.snippet("조선왕조 법궁 어쩌구");
//            mMap.addMarker(marker);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case 1:
                if((grantResults.length >0) &&
                        (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    drawMap();
                }
                break;

            default:
                break;
        }

    }
}
