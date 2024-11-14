package com.example.googlemap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

import java.io.IOException;
import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        assert mapFragment != null;
        mapFragment.getMapAsync(this);




    }

    @Override
    public void onMapReady(@NonNull GoogleMap mMap) {
        LatLng latLng=new LatLng(30.3431,73.3894);
        MarkerOptions markerOptions =new MarkerOptions().position(latLng).title("Nasir");
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,16f));
     //Circle add
        mMap.addCircle(new CircleOptions()
                .center(latLng).radius(1000)
                .fillColor(Color.GREEN)
                .strokeColor(Color.DKGRAY));
        //Polygon
        mMap.addPolygon(new PolygonOptions().add(new LatLng( 30.3431, 73.3894),
                new LatLng(30.3431,74.3894),
                new LatLng(31.3431,74.3894),
                new LatLng(31.3431,73.3894),
                new LatLng(30.3431,73.3894)).fillColor(Color.YELLOW).strokeColor(Color.BLUE));
        mMap.addGroundOverlay(new GroundOverlayOptions().position(latLng,1000f,1000f)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.download))
                .clickable(true));


        //GeoCoder

       mMap.setOnMapClickListener(latLng1 -> {
           mMap.addMarker(new MarkerOptions().position(latLng1).title("Clicked Here"));
           Geocoder geocoder=new Geocoder(MapsActivity.this);
           try {
               ArrayList<Address> arrdr =(ArrayList<Address>) geocoder.getFromLocation(latLng1.latitude, latLng1.longitude,1);
               assert arrdr != null;
               Log.d("Addr",arrdr.get(0).getAddressLine(0));
           }
           catch (IOException e) {
               e.printStackTrace();
           }
       });



    }
}