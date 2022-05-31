package com.equipo1.matexpres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * TODO: Hacer una pantalla emergente al momento de presionar el botón de editar avatar o el imagebutton del avatar
 * La pantalla emergente reinicia la activity Perfil o hace el cambio de avatar instantáneamente
 * TODO: Botón del teclado Enter actualiza la BD al cambiar el nombre de usuario
 */

public class Perfil extends AppCompatActivity implements View.OnKeyListener
{
    private ImageView ivAvatar, avatar;
    private EditText etUsername;
    private TextView tvNivelGral, tvNumExp, tvSuma, tvResta, tvMulti, tvDivi;
    String numUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        etUsername = findViewById(R.id.etUsername);
        tvNivelGral = findViewById(R.id.tvNumNivelGen);
        tvNumExp = findViewById(R.id.tvNumExp);
        tvSuma = findViewById(R.id.tvNivelSuma);
        tvResta = findViewById(R.id.tvNivelResta);
        tvMulti = findViewById(R.id.tvNivelMulti);
        tvDivi = findViewById(R.id.tvNivelDivision);
        ivAvatar = findViewById(R.id.ivEditarAvatar);
        avatar = findViewById(R.id.ivAvatar);

        numUser = getIntent().getStringExtra("num_user");

        etUsername.setOnKeyListener(this);

        ivAvatar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Perfil.this, CambioAvatar.class);
                intent.putExtra("num_user", numUser);
                startActivity(intent);

            }
        });

        mostrar();
    }



    private void mostrar()
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BD", null, 1);
        SQLiteDatabase BD = admin.getWritableDatabase();

        Cursor consulta = BD.rawQuery(
                "select * from jugador where num_user = " + numUser, null);

        if ( consulta.moveToFirst() )
        {
            // Avatar
            int numAvatar = consulta.getInt(2);
            switch ( numAvatar )
            {
                case 3:
                    avatar.setImageResource(R.drawable.gato3);
                    break;
                case 5:
                    avatar.setImageResource(R.drawable.gato5);
                    break;
                case 8:
                    avatar.setImageResource(R.drawable.gato8);
                    break;
                case 4:
                    avatar.setImageResource(R.drawable.gato4);
                    break;
                case 1:
                    avatar.setImageResource(R.drawable.gato1);
                    break;
                default:
                    avatar.setImageResource(R.drawable.gato4);
                    break;
            }

            // Usuario
            etUsername.setText(consulta.getString(0));

            // Nivel de suma
            int nivelSuma = consulta.getInt(3);
            tvSuma.setText("Nivel " + nivelSuma);

            // Nivel de resta
            int nivelResta = consulta.getInt(4);
            tvResta.setText("Nivel " + nivelResta);

            // Nivel de multiplicación
            int nivelMultiplicacion = consulta.getInt(5);
            tvMulti.setText("Nivel " + nivelMultiplicacion);

            // Nivel de división
            int nivelDivision = consulta.getInt(6);
            tvDivi.setText("Nivel " + nivelDivision);

            // Nivel de experiencia
            tvNumExp.setText(""+consulta.getInt(7));
            // Nivel general
            int nivelGral = consulta.getInt(8) + nivelSuma + nivelResta + nivelMultiplicacion + nivelDivision;
            tvNivelGral.setText(""+nivelGral);
        }
    }

    public void salir(View view)
    {
        Intent intent = new Intent(this, CerrarSesion.class);
        startActivity(intent);
    }

    public void menu(View view)
    {
        Intent intent = new Intent(this, Menu.class);
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

    public void preguntas(View view)
    {
        Intent intent = new Intent(this, Preguntas.class);
        intent.putExtra("num_user", numUser);
        startActivity(intent);
        finish();
    }

    public void suma(View view)
    {
        Intent intent = new Intent(this, Menu.class);
        intent.putExtra("num_user", numUser);
        startActivity(intent);
        finish();
    }

    // Regresar a la MainActivity
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, SeleccionarUsuario.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event)
    {
        if ( (event.getAction()==KeyEvent.ACTION_DOWN) && (keyCode==KeyEvent.KEYCODE_ENTER) )
        {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BD", null, 1);
            SQLiteDatabase BD = admin.getWritableDatabase();

            if ( !etUsername.getText().equals("") )
            {
                ContentValues nivelCompletado = new ContentValues();
                nivelCompletado.put("username", etUsername.getText().toString());
                BD.update("jugador", nivelCompletado, "num_user = " + numUser, null);
                Toast.makeText(this, "¡Modificación realizada! Reinicia la app para notar el cambio",
                               Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Debes ingresar un nombre", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }
}