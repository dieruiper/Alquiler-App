package com.example.mapaestaciones;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telecom.Call;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Mapa_con_las_oficinas extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowLongClickListener {
    //comentario
    private GoogleMap mMap;
    private Marker InfoWindowBarcelona,InfoWindowBilbao,InfoWindowSevilla,InfoWindowMadrid, InfoWindowVigo;

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


        /*
        ArrayList<String> listaNombres = new ArrayList<String>();

        ArrayList<Double> listaLatitudes = new ArrayList<Double>();
        ArrayList<Double> listaLongitudes = new ArrayList<Double>();
        ArrayList<LatLng> listaMarcadores = new ArrayList<LatLng>();

        //Obtener datos BD
        for (int i = 0; i<listaNombres.size(); i++){
            listaNombres.add(listaBDNombres.get(i));
             LatLng posicion = new LatLng(listaBDLatitudes.get(i),listaBDLongitudes.get(i));
             listaMarcadores.add(posicion);
        }

        //Crear marcadores
        for(int i = 0; i<listaMarcadores.size(); i++){
            for(int j = 0; j<listaNombres.size();j++){
                googleMap.addMarker(new MarkerOptions().position(listaMarcadores.get(i)).title(String.valueOf(listaNombres.get(j))).snippet("Mantenga pulsado aquí para confirmar").icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));
            }
          mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(listaMarcadores.get(i), 5));
        }
        */

        LatLng sevilla = new LatLng(37.3886303, -5.9953403);
        InfoWindowSevilla =  googleMap.addMarker(new MarkerOptions().position(sevilla).title("Sevilla").snippet("Mantenga pulsado aquí para confirmar").icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));

        LatLng madrid = new LatLng(40.4167047, -3.7035825);
        InfoWindowMadrid =  googleMap.addMarker(new MarkerOptions().position(madrid).title("Madrid").snippet("Mantenga pulsado aquí para confirmar").icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));

        LatLng barcelona = new LatLng(41.3828939, 2.1774322);
        InfoWindowBarcelona =  googleMap.addMarker(new MarkerOptions().position(barcelona).title("Barcelona").snippet("Mantenga pulsado aquí para confirmar").icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));

        LatLng bilbao = new LatLng(43.2630018, -2.9350039);
        InfoWindowBilbao =  googleMap.addMarker(new MarkerOptions().position(bilbao).title("Bilbao").snippet("Mantenga pulsado aquí para confirmar").icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));

        LatLng vigo = new LatLng(42.2376602, -8.7247205);
        InfoWindowVigo = googleMap.addMarker(new MarkerOptions().position(vigo).title("Vigo").snippet("Mantenga pulsado aquí para confirmar").icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));
        //Camara al inicio
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(madrid, 5));



        //Habilitar zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnInfoWindowLongClickListener(this);
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
    public void onInfoWindowLongClick(Marker marker) {
        String nombreMarker = "";
        /*
        for (int i = 0; i<listaNombres.size(); i++){
            if (marker.getTitle().equals(listaNombres.get(i)){
                nombreMarker = listaNombres.get(i);
         */

        if (marker.equals(InfoWindowSevilla)) {
            nombreMarker = InfoWindowSevilla.getTitle();
        }else if (marker.equals(InfoWindowBilbao)) {
            nombreMarker = InfoWindowBilbao.getTitle();
        }else if (marker.equals(InfoWindowVigo)) {
            nombreMarker = InfoWindowVigo.getTitle();
        }else if (marker.equals(InfoWindowMadrid)) {
            nombreMarker = InfoWindowMadrid.getTitle();
        }else if (marker.equals(InfoWindowBarcelona)) {
            nombreMarker = InfoWindowBarcelona.getTitle();
        }
        Intent IntentMarcadores = new Intent(Mapa_con_las_oficinas.this, Activity_Reservar_Vehiculos.class);
        IntentMarcadores.putExtra("title", nombreMarker);
        startActivity(IntentMarcadores);
    }
}