package com.example.snortdroid.maps;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.snortdroid.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String city;
    private Double latitude,longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //obtain lat and lng for desired city
        if(getIntent().getExtras()!=null){
            latitude= Double.valueOf(getIntent().getStringExtra("latitude"));
            longitude = Double.valueOf(getIntent().getStringExtra("longitude"));
            city=getIntent().getStringExtra("city");
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

         LatLng cityMarker = new LatLng(latitude, longitude);
           mMap.addMarker(new MarkerOptions().position(cityMarker).title(city));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cityMarker));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cityMarker,18));

        mMap.addCircle(new CircleOptions()
                .center(cityMarker)
                .radius(1000000));

//        mMap.addPolygon((new PolygonOptions())
//                .add(new LatLng(-35.016, 143.321),
//                        new LatLng(-34.747, 145.592),
//                        new LatLng(-34.364, 147.891),
//                        new LatLng(-33.501, 150.217),
//                        new LatLng(-32.306, 149.248),
//                        new LatLng(-32.491, 147.309)));
//        mMap.addMarker(new MarkerOptions().position(new LatLng(-35.016, 143.321)));
//        mMap.addMarker(new MarkerOptions().position(new LatLng(-34.747, 145.592)));
//        mMap.addMarker(new MarkerOptions().position(new LatLng(-34.364, 147.891)));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-32.491, 147.309)));





    }
}
