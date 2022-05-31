package com.equipo1.matexpres;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

public class Revision_Incorrecta extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision_incorrecta);

        DisplayMetrics medidas = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidas);

        int ancho = medidas.widthPixels;
        int alto = medidas.heightPixels;

        getWindow().setLayout((int) (ancho * 0.85), (int) (alto * 0.45));

        getSupportActionBar().hide();
    }

    public void cerrar(View view)
    {
        finish();
    }
}