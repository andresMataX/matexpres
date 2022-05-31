package com.equipo1.matexpres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ListaEjercicios extends AppCompatActivity
{
    String numUser;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        numUser = getIntent().getStringExtra("num_user");
    }

    public void menu(View view)
    {
        Intent intent = new Intent(this, Menu.class);
        intent.putExtra("num_user", numUser);
        startActivity(intent);
        finish();
    }

    public void resta(View view)
    {
        Intent intent = new Intent(this, Resta.class);
        intent.putExtra("num_user", numUser);
        startActivity(intent);
        finish();
    }

    public void multi(View view)
    {
        Intent intent = new Intent(this, Multiplicacion.class);
        intent.putExtra("num_user", numUser);
        startActivity(intent);
        finish();
    }

    public void div(View view)
    {
        Intent intent = new Intent(this, Division.class);
        intent.putExtra("num_user", numUser);
        startActivity(intent);
        finish();
    }

    public void salir(View view)
    {
        Intent intent = new Intent(this, SeleccionarUsuario.class);
        startActivity(intent);
        finish();
    }
}