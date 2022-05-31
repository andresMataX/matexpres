package com.equipo1.matexpres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class SeleccionarUsuario extends AppCompatActivity
{
    // Referencias con componentes
    private ImageButton ibEditar;
    private Button btnUsuario1, btnUsuario2, btnUsuario3, btnUsuario4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_usuario);

        // Quitar la Action Bar
        getSupportActionBar().hide();

        // Referencias con GUI
        btnUsuario1 = findViewById(R.id.btnUsuario1);
        btnUsuario2 = findViewById(R.id.btnUsuario2);
        btnUsuario3 = findViewById(R.id.btnUsuario3);
        btnUsuario4 = findViewById(R.id.btnUsuario4);

        generar();
        cntUsuarios(0);
    }

    public void generar()
    {
        // Conexión a base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BD", null, 1);
        // Modo de escritura y lectura de la base de datos
        SQLiteDatabase BD = admin.getWritableDatabase();

        Cursor consulta = BD.rawQuery(
                "select username, avatar from jugador where num_user = 1", null);
        Cursor consulta2 = BD.rawQuery(
                "select username, avatar from jugador where num_user = 2", null);
        Cursor consulta3 = BD.rawQuery(
                "select username, avatar from jugador where num_user = 3", null);
        Cursor consulta4 = BD.rawQuery(
                "select username, avatar from jugador where num_user = 4", null);

        // Revisar que existan al menos un usuario registrado
        if ( consulta.moveToFirst() && consulta2.moveToFirst() && consulta3.moveToFirst() && consulta4.moveToFirst() )
        {
            // Selecciona cada nombre de usuario y lo muestra en la GUI
            String user1, user2, user3, user4;
            int avatar1, avatar2, avatar3, avatar4;
            user1 = consulta.getString(0);
            user2 = consulta2.getString(0);
            user3 = consulta3.getString(0);
            user4 = consulta4.getString(0);

            btnUsuario1.setText(user1);
            btnUsuario2.setText(user2);
            btnUsuario3.setText(user3);
            btnUsuario4.setText(user4);

            // Selecciona el avatar del usuario y lo asgina a los botones
            avatar1 = consulta.getInt(1);
            avatar2 = consulta2.getInt(1);
            avatar3 = consulta3.getInt(1);
            avatar4 = consulta4.getInt(1);

            btnUsuario1.setCompoundDrawablesWithIntrinsicBounds(null, avatares(avatar1), null,
                                                                null);
            btnUsuario2.setCompoundDrawablesWithIntrinsicBounds(null, avatares(avatar2), null,
                                                                null);
            btnUsuario3.setCompoundDrawablesWithIntrinsicBounds(null, avatares(avatar3), null,
                                                                null);
            btnUsuario4.setCompoundDrawablesWithIntrinsicBounds(null, avatares(avatar4), null,
                                                                null);

            // BORRAR BD
            //BD.delete("jugador", "", null);
            // Cursor consulta = BD.rawQuery("select <columna> from <tabla> where <condicion> =" + variable, null );
        }
        else
        {
            // Generamos los 4 usuarios genéricos
            // Registrar valores
            ContentValues insertarUsu1 = new ContentValues();
            // Campo o columna, variable
            // insertar.put("nombre", nombre_jugador);
            insertarUsu1.put("username", "Usuario1");
            insertarUsu1.put("num_user", 1);
            insertarUsu1.put("avatar", 1);
            insertarUsu1.put("niv_sum", 0);
            insertarUsu1.put("niv_res", 0);
            insertarUsu1.put("niv_mul", 0);
            insertarUsu1.put("niv_div", 0);
            insertarUsu1.put("exp", 0);
            insertarUsu1.put("nivel", 0);
            // Insertamos los valores dentro de la tabla
            BD.insert("jugador", null, insertarUsu1);

            ContentValues insertarUsu2 = new ContentValues();
            // Usuario próximo a ser "creado"
            insertarUsu2.put("username", "Usuario2");
            insertarUsu2.put("num_user", 2);
            insertarUsu2.put("avatar", 6);
            insertarUsu2.put("niv_sum", 0);
            insertarUsu2.put("niv_res", 0);
            insertarUsu2.put("niv_mul", 0);
            insertarUsu2.put("niv_div", 0);
            insertarUsu2.put("exp", 0);
            insertarUsu2.put("nivel", 0);
            // Insertamos los valores dentro de la tabla
            BD.insert("jugador", null, insertarUsu2);

            ContentValues insertarUsu3 = new ContentValues();
            // Usuario escondido
            insertarUsu3.put("username", "Usuario3");
            insertarUsu3.put("num_user", 3);
            insertarUsu3.put("avatar", 3);
            insertarUsu3.put("niv_sum", 0);
            insertarUsu3.put("niv_res", 0);
            insertarUsu3.put("niv_mul", 0);
            insertarUsu3.put("niv_div", 0);
            insertarUsu3.put("exp", 0);
            insertarUsu3.put("nivel", 0);
            // Insertamos los valores dentro de la tabla
            BD.insert("jugador", null, insertarUsu3);

            ContentValues insertarUsu4 = new ContentValues();
            // Usuario escondido
            insertarUsu4.put("username", "Usuario4");
            insertarUsu4.put("num_user", 4);
            insertarUsu4.put("avatar", 4);
            insertarUsu4.put("niv_sum", 0);
            insertarUsu4.put("niv_res", 0);
            insertarUsu4.put("niv_mul", 0);
            insertarUsu4.put("niv_div", 0);
            insertarUsu4.put("exp", 0);
            insertarUsu4.put("nivel", 0);
            // Insertamos los valores dentro de la tabla
            BD.insert("jugador", null, insertarUsu4);

            ContentValues ava = new ContentValues();
            ava. put("cnt_usuarios", 3);
            BD.insert("usuarios", null, ava);
        }
    }

    private Drawable avatares(int num_avatar)
    {
        switch ( num_avatar )
        {
            case 1:
                Drawable img = getResources().getDrawable(R.drawable.gato1);
                return img;
            case 2:
                Drawable img2 = getResources().getDrawable(R.drawable.gato2);
                return img2;
            case 3:
                Drawable img3 = getResources().getDrawable(R.drawable.gato3);
                return img3;
            case 4:
                Drawable img4 = getResources().getDrawable(R.drawable.gato4);
                return img4;
            case 5:
                Drawable img5 = getResources().getDrawable(R.drawable.gato5);
                return img5;
            case 6:
                Drawable img6 = getResources().getDrawable(R.drawable.gato6);
                return img6;
            case 8:
                Drawable img8 = getResources().getDrawable(R.drawable.gato8);
                return img8;
        }
        return null;
    }

    // Método que verifica el número de usuarios registrados
    private void cntUsuarios(int decremento)
    {
        // Conexión a base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BD", null, 1);
        // Modo de escritura y lectura de la base de datos
        SQLiteDatabase BD = admin.getWritableDatabase();

        Cursor consulta = BD.rawQuery(
                "select * from usuarios", null);

        if ( consulta.moveToFirst() )
        {
            int cntUsuarios = consulta.getInt(0);
            cntUsuarios -= decremento;

            // Modificar la cantidad de usuarios en caso de tocar un botón con avatar de Gato6
            ContentValues decre = new ContentValues();
            // PARA REINICIAR Y TESTEAR, cambiar a 3 en vez de cntUsuarios
            decre.put("cnt_usuarios", cntUsuarios);
            BD.update("usuarios", decre, "", null);
            // PARA REINICIAR Y TESTEAR, cambiar a 3 en vez de cntUsuarios
            int resultado = 4 - cntUsuarios;

            switch ( resultado )
            {
                case 1:
                    // Modificar al usuario2 el avatar de gato6 en BD
                    ContentValues modificacion = new ContentValues();
                    modificacion.put("avatar", 6);
                    BD.update("jugador", modificacion, "num_user=" + 2, null);
                    // Ocultar usuario3 y 4
                    btnUsuario3.setVisibility(View.INVISIBLE);
                    btnUsuario4.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    // Agregar gato1 por defecto
                    ContentValues caso2 = new ContentValues();
                    caso2.put("avatar", 1);
                    BD.update("jugador", caso2, "num_user=" + 2, null);
                    // Modificar al usuario3 el avatar de gato6 en BD
                    ContentValues modificacion2 = new ContentValues();
                    modificacion2.put("avatar", 6);
                    BD.update("jugador", modificacion2, "num_user=" + 3, null);
                    // Ocultar usuario4
                    btnUsuario4.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    // Agregar gato1 por defecto
                    ContentValues caso3 = new ContentValues();
                    caso3.put("avatar", 1);
                    BD.update("jugador", caso3, "num_user=" + 3, null);
                    // Modificar al usuario4 el avatar de gato6 en BD
                    ContentValues modificacion3 = new ContentValues();
                    modificacion3.put("avatar", 6);
                    BD.update("jugador", modificacion3, "num_user=" + 4, null);
                    break;
                case 4:
                    // Modificar al usuario4 el avatar de gato1 en BD
                    ContentValues modificacion4 = new ContentValues();
                    modificacion4.put("avatar", 1);
                    BD.update("jugador", modificacion4, "num_user=" + 4, null);
                    // No ocultar usuarios
                    break;
            }
        }
    }

    // Regresar a la MainActivity
    @Override
    public void onBackPressed()
    {
        // TODO: Regresar al Main Activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void preguntas(View view)
    {
        Intent intent = new Intent(this, Preguntas.class);
        startActivity(intent);
        finish();
    }

    public void avanzar(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BD", null, 1);
        SQLiteDatabase BD = admin.getWritableDatabase();

        Intent intent = new Intent(this, Perfil.class);
        if ( view.getId() == btnUsuario1.getId() )
        {
            intent.putExtra("num_user", "1");
            cntUsuarios(0);
        }
        else if ( view.getId() == btnUsuario2.getId() )
        {
            intent.putExtra("num_user", "2");

            Cursor consulta = BD.rawQuery(
                    "select avatar from jugador where num_user = 2", null);
            if ( consulta.moveToFirst() )
            {
                int avatar = consulta.getInt(0);
                if ( avatar == 6 )
                {
                    cntUsuarios(1);
                }
                else
                {
                    cntUsuarios(0);
                }
            }
        }
        else if ( view.getId() == btnUsuario3.getId() )
        {
            intent.putExtra("num_user", "3");

            Cursor consulta = BD.rawQuery(
                    "select avatar from jugador where num_user = 3", null);
            if ( consulta.moveToFirst() )
            {
                int avatar = consulta.getInt(0);
                if ( avatar == 6 )
                {
                    cntUsuarios(1);
                }
                else
                {
                    cntUsuarios(0);
                }
            }
        }
        else if ( view.getId() == btnUsuario4.getId() )
        {
            intent.putExtra("num_user", "4");

            Cursor consulta = BD.rawQuery(
                    "select avatar from jugador where num_user = 4", null);
            if ( consulta.moveToFirst() )
            {
                int avatar = consulta.getInt(0);
                if ( avatar == 6 )
                {
                    cntUsuarios(1);
                }
                else
                {
                    cntUsuarios(0);
                }
            }
        }
        startActivity(intent);
        finish();
    }
}