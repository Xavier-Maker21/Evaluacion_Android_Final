package com.example.gestor_actividad2;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gestor_actividad2.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btn_Volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        btn_Volver=findViewById(R.id.btn_Volver);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btn_Volver.setOnClickListener(this::volver);
    }

    public void volver(View view){
        Intent oIntent2=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(oIntent2);
        finish();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sTomas = new LatLng(-34.171856, -70.736348);
        mMap.addMarker(new MarkerOptions().position(sTomas).title("IP Santo Tomas"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sTomas));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sTomas,14));
        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}