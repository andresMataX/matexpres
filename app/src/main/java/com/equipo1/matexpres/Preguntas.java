package com.equipo1.matexpres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Preguntas extends AppCompatActivity
{
    String numUser;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);
        numUser = getIntent().getStringExtra("num_user");
    }

    public void pregunta1(View view)
    {
        Intent intent = new Intent(this, Pregunta1.class);
        intent.putExtra("num_user", numUser);
        startActivity(intent);
        finish();
    }
    public void pregunta2(View view)
    {
        Intent intent = new Intent(this, Pregunta2.class);
        intent.putExtra("num_user", numUser);
        startActivity(intent);
        finish();
    }
    public void pregunta3(View view)
    {
        Intent intent = new Intent(this, Pregunta3.class);
        intent.putExtra("num_user", numUser);
        startActivity(intent);
        finish();
    }
    public void pregunta4(View view)
    {
        Intent intent = new Intent(this, Pregunta4.class);
        intent.putExtra("num_user", numUser);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, SeleccionarUsuario.class);
        intent.putExtra("num_user", numUser);
        startActivity(intent);
        finish();
    }
}