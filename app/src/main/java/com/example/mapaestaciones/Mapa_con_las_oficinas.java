package com.example.mapaestaciones;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
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
    ArrayList<String> listaNombres = new ArrayList<String>();
    ArrayList<LatLng> listaPosiciones = new ArrayList<LatLng>();
    LatLng marcador = null;
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


        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor fila = BaseDeDatos.rawQuery("select * from oficinas", null);
        if(fila.moveToFirst()){
            do{
                Oficina oficina = new Oficina(fila.getString(0),fila.getDouble(1),
                        fila.getDouble(2));
                listaNombres.add(oficina.getNombre());
                marcador = new LatLng(oficina.getLatitude(),oficina.getLongitude());
                listaPosiciones.add(marcador);
            }while (fila.moveToNext());
        }
        BaseDeDatos.close();
        for(int i = 0; i<listaPosiciones.size(); i++){
            googleMap.addMarker(new MarkerOptions().position(listaPosiciones.get(i)).title(listaNombres.get(i)).snippet("Mantenga pulsado aquí para confirmar").icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(listaPosiciones.get(i), 5));
        }


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

        for (int i = 0; i<listaNombres.size(); i++){
            if (marker.getTitle().equals(listaNombres.get(i))) {
                nombreMarker = listaNombres.get(i);
            }
        }

        Intent IntentMarcadores = new Intent(Mapa_con_las_oficinas.this, Activity_Reservar_Vehiculos.class);
        IntentMarcadores.putExtra("title", nombreMarker);
        startActivity(IntentMarcadores);
    }
}