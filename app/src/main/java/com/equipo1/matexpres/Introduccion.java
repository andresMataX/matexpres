package com.equipo1.matexpres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

public class Introduccion extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduccion2);

        DisplayMetrics medidas = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidas);

        int ancho = medidas.widthPixels;
        int alto = medidas.heightPixels;

        getWindow().setLayout((int) (ancho * 0.85), (int) (alto * 0.70));

        getSupportActionBar().hide();
    }

    public void cerrar(View view)
    {
        finish();
    }

    public void teoria(View view)
    {
        Intent intent = new Intent(this, Teoria.class);
        startActivity(intent);
        finish();
    }
}