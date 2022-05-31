package com.equipo1.matexpres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ocultar la Action Bar
        getSupportActionBar().hide();
    }

    // Método que te lleva a la siguiente ventana
    public void iniciar(View view)
    {
        Intent intent = new Intent(this, SeleccionarUsuario.class);
        startActivity(intent);
        finish();
    }

    // Botón regresar
    @Override
    public void onBackPressed()
    {
        // Evitar que el botón atrás reinicie la app
    }
}