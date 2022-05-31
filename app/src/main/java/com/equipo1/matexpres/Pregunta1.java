package com.equipo1.matexpres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Pregunta1 extends AppCompatActivity
{
    String numUser;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta1);
        numUser = getIntent().getStringExtra("num_user");
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, Preguntas.class);
        intent.putExtra("num_user", numUser);
        startActivity(intent);
        finish();
    }
}