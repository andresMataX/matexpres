package com.equipo1.matexpres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity
{
    String numUser;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        numUser = getIntent().getStringExtra("num_user");
    }

    public void suma(View view)
    {
        Intent intent = new Intent(this, Suma.class);
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

    public void divi(View view)
    {
        Intent intent = new Intent(this, Division.class);
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

    public void perfil(View view)
    {
        Intent intent = new Intent(this, Perfil.class);
        intent.putExtra("num_user", numUser);
        startActivity(intent);
        finish();
    }

    public void salir(View view)
    {
        Intent intent = new Intent(this, SeleccionarUsuario.class);
        intent.putExtra("num_user", numUser);
        startActivity(intent);
        finish();
    }

    public void preguntas(View view)
    {
        Intent intent = new Intent(this, Preguntas.class);
        intent.putExtra("num_user", numUser);
        startActivity(intent);
        finish();
    }

    public void buzon(View view)
    {
        Intent intent = new Intent(this, Buzon.class);
        intent.putExtra("num_user", numUser);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, Perfil.class);
        intent.putExtra("num_user", numUser);
        startActivity(intent);
        finish();
    }
}