package com.equipo1.matexpres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Teoria extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teoria);
    }

    public void menu(View view)
    {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
        finish();
    }

    public void salir(View view)
    {
        Intent intent = new Intent(this, SeleccionarUsuario.class);
        startActivity(intent);
        finish();
    }

    public void ejercicios(View view)
    {
        Intent intent = new Intent(this, ListaEjercicios.class);
        startActivity(intent);
        finish();
    }
}