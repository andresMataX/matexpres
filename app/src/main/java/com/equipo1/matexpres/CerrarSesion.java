package com.equipo1.matexpres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

public class CerrarSesion extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerrar_sesion);

        DisplayMetrics medidas = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidas);

        int ancho = medidas.widthPixels;
        int alto = medidas.heightPixels;

        getWindow().setLayout((int) (ancho * 0.85), (int) (alto * 0.35));

        getSupportActionBar().hide();
    }

    public void salir(View view)
    {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void no(View view)
    {
        finish();
    }
}