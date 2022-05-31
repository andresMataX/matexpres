package com.equipo1.matexpres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class CambioAvatar extends AppCompatActivity
{
    private ImageView gato1, gato2, gato3, gato4, gato5;
    String numUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_avatar);

        gato1 = findViewById(R.id.gato1);
        gato2 = findViewById(R.id.gato2);
        gato3 = findViewById(R.id.gato3);
        gato4 = findViewById(R.id.gato4);
        gato5 = findViewById(R.id.gato5);

        numUser = getIntent().getStringExtra("num_user");

        DisplayMetrics medidas = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidas);

        int ancho = medidas.widthPixels;
        int alto = medidas.heightPixels;

        getWindow().setLayout((int) (ancho * 0.85), (int) (alto * 0.70));

        getSupportActionBar().hide();
    }

    public void cambiarAvatar(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BD", null, 1);
        SQLiteDatabase BD = admin.getWritableDatabase();

        if ( view.getId() == gato1.getId() )
        {
            ContentValues nivelCompletado = new ContentValues();
            nivelCompletado.put("avatar", 3);
            BD.update("jugador", nivelCompletado, "num_user = " + numUser, null);
            Toast.makeText(this, "Modificación realizada, reinicia la aplicación",
                           Toast.LENGTH_SHORT).show();
        }
        else if ( view.getId() == gato2.getId() )
        {
            ContentValues nivelCompletado = new ContentValues();
            nivelCompletado.put("avatar", 5);
            BD.update("jugador", nivelCompletado, "num_user = " + numUser, null);
            Toast.makeText(this, "Modificación realizada, reinicia la aplicación",
                           Toast.LENGTH_SHORT).show();
        }
        else if ( view.getId() == gato3.getId() )
        {
            ContentValues nivelCompletado = new ContentValues();
            nivelCompletado.put("avatar", 8);
            BD.update("jugador", nivelCompletado, "num_user = " + numUser, null);
            Toast.makeText(this, "Modificación realizada, reinicia la aplicación",
                           Toast.LENGTH_SHORT).show();
        }
        else if ( view.getId() == gato4.getId() )
        {
            ContentValues nivelCompletado = new ContentValues();
            nivelCompletado.put("avatar", 4);
            BD.update("jugador", nivelCompletado, "num_user = " + numUser, null);
            Toast.makeText(this, "Modificación realizada, reinicia la aplicación",
                           Toast.LENGTH_SHORT).show();
        }
        else
        {
            ContentValues nivelCompletado = new ContentValues();
            nivelCompletado.put("avatar", 1);
            BD.update("jugador", nivelCompletado, "num_user = " + numUser, null);
            Toast.makeText(this, "Modificación realizada, reinicia la aplicación",
                           Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}