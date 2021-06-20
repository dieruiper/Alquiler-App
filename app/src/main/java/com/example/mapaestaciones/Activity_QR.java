package com.example.mapaestaciones;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.example.mapaestaciones.R.layout.*;

public class Activity_QR extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView myScanerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Slide slide = new Slide(Gravity.BOTTOM);
        slide.setDuration(MainActivity.DURATION_TRANSITION);
        slide.setInterpolator(new DecelerateInterpolator());

        getWindow().setEnterTransition(slide);


        setContentView(activity_qr);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
    }

    public void bntEscaner(View view){
        //Creamos la variable
         myScanerView = new ZXingScannerView(this);
         //que queremos que muestre
         setContentView(myScanerView);
         //necesitamos acceder al metodo de resultHandel para poder resolver el codigo y mostrar el código por pantalla
         myScanerView.setResultHandler(this);
         //se necesitan los permisos para usar la camara, se dan e forma manual; se podría pedir por código.
         myScanerView.startCamera();
    }


    //Se encarga de escanear el código y traducirlo
    @Override
    public void handleResult(Result result) {
        //Para que se muestre lo que acabamos de escanear
        Log.v("HandleResult",result.getText());
        //lo pasamos por un alertDialog aunq podría usarse otra cosa
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //El resultado que acabamos de escanear lo pasamos a texto
        builder.setMessage(result.getText());
        //En esta parte se crea el dialogo
        AlertDialog alertDialog = builder.create();
        //lo mostramos
        alertDialog.show();

        //por si queremos seguir escaneando
        //myScanerView.resumeCameraPreview(this);
    }
}