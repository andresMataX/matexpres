package com.equipo1.matexpres;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper
{
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BD)
    {
        // Creamos las tablas de la base de datos
        // BD.execSQL("create table puntaje(nombre text, score int)");
        BD.execSQL("create table jugador(username text, num_user int, avatar int, niv_sum int, niv_res int, niv_mul int, niv_div int, exp int, nivel int)");
        BD.execSQL("create table usuarios(cnt_usuarios int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
