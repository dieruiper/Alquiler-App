package com.example.mapaestaciones;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapa_con_las_oficinas extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {
    //comentario
    private GoogleMap mMap;
    private Marker markerBilbao, InfoWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_con_las_oficinas);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sevilla = new LatLng(37.3886303, -5.9953403);
        mMap.addMarker(new MarkerOptions().position(sevilla).title("Oficina en Sevilla").snippet("Esta es la oficina de Sevilla").icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));

        LatLng madrid = new LatLng(40.4167047, -3.7035825);
        mMap.addMarker(new MarkerOptions().position(madrid).title("Oficina en Madrid").snippet("Esta es la oficina de Madrid").icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));

        LatLng barcelona = new LatLng(41.3828939, 2.1774322);
        mMap.addMarker(new MarkerOptions().position(barcelona).title("Oficina en Barcelona").snippet("Esta es la oficina de Barcelona").icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));

        LatLng bilbao = new LatLng(43.2630018, -2.9350039);
        markerBilbao = googleMap.addMarker(new MarkerOptions().position(bilbao).title("Oficina en Bilbao").snippet("Esta es la oficina de Bilbao").icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));

        LatLng vigo = new LatLng(42.2376602, -8.7247205);
        InfoWindow = googleMap.addMarker(new MarkerOptions().position(vigo).title("VIGO").icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));

        //Camara al inicio
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(madrid, 5));

        //Click en marcador
        googleMap.setOnMarkerClickListener(this);

        //Cuadro dialogo
        googleMap.setOnInfoWindowClickListener(this);

        //Habilitar zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);

        //Habilitar posicion actual
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(marker.equals(markerBilbao)){
            String lat, lng;
            lat = Double.toString(marker.getPosition().latitude);
            lng = Double.toString(marker.getPosition().longitude);
            Toast.makeText(this,"Oficina Bilbao: "+lat+","+lng, Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (marker.equals(InfoWindow)) {
            VigoDialogo.newIntance(marker.getTitle(), getString(R.string.VigoInfo)).show(getSupportFragmentManager(),null);
        }
    }
}