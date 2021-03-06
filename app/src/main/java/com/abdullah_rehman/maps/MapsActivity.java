package com.abdullah_rehman.maps;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.ButtCap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button btnMove;
    EditText etLocation;
    Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnMove = (Button) findViewById(R.id.btnMove);
        etLocation = (EditText) findViewById(R.id.etLocation);

        geocoder = new Geocoder(MapsActivity.this);

        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(etLocation.getText())){

                }else{
                    String locationStr = etLocation.getText().toString();
                    try {
                        List<Address> ourLocations = geocoder.getFromLocationName(locationStr, 1);
                        if(ourLocations.size() > 0){
                            Address requiredLocation = ourLocations.get(0);
                            double latitude = requiredLocation.getLatitude();
                            double longitude = requiredLocation.getLongitude();
                            String locationTitle = requiredLocation.getFeatureName();
                            LatLng faisalabad = new LatLng(latitude,longitude);
                            mMap.addMarker(new MarkerOptions().position(faisalabad).title(locationTitle));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(faisalabad));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
