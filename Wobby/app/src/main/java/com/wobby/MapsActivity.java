package com.wobby;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DBHelper db;

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
        db = new DBHelper(getApplicationContext());

        // Add a marker in Tec and move the camera
        LatLng tec = new LatLng(20.734574, -103.455687);
        mMap.addMarker(new MarkerOptions().position(tec).title("ITESM GDL").snippet("Aqui estudiamos"));
        final CameraPosition TEC = new CameraPosition.Builder().target(tec).zoom(15.5f).bearing(0).tilt(25).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(TEC));
        refreshPins();
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng point) {
                MarkerOptions marker = new MarkerOptions().position(point).title("Custom location").snippet("test").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                mMap.addMarker(marker);
                db.add(marker);
                refreshPins();
            }
        });
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(getApplicationContext(), PinDetailActivity.class);
                intent.putExtra("Title", marker.getTitle());
                startActivity(intent);
            }
        });
    }

    private void refreshPins(){
        ArrayList<MarkerOptions> pins = db.getAllPins();
        for (int i = 0; i < pins.size(); i++){
            mMap.addMarker(pins.get(i));
        }
    }
}
