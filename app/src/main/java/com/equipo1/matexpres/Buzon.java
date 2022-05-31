package com.equipo1.matexpres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Buzon extends AppCompatActivity
{
    String numUser;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzon);
        numUser = getIntent().getStringExtra("num_user");
    }

    public void mensaje(View view)
    {
        Intent intent = new Intent(this, Mensaje.class);
        startActivity(intent);
    }

    public void menu(View view)
    {
        Intent intent = new Intent(this, Menu.class);
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

    public void perfil(View view)
    {
        Intent intent = new Intent(this, Perfil.class);
        intent.putExtra("num_user", numUser);
        startActivity(intent);
        finish();
    }
}