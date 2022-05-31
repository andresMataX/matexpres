package com.equipo1.matexpres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Resta extends AppCompatActivity
{

    private ImageView ivVidas;
    private TextView tvNivel, tvSumando1, tvSumando2;
    private Button btnRe1, btnRe2, btnRe3, btnRe4;

    int numAleatorioUno, numAleatorioDos, resultado, vidas = 5, nivel;
    int btnCorrecto;
    String numUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resta);

        ivVidas = findViewById(R.id.vidas);
        tvNivel = findViewById(R.id.nivel);
        tvSumando1 = findViewById(R.id.sumando1);
        tvSumando2 = findViewById(R.id.sumando2);
        btnRe1 = findViewById(R.id.btnRespuesta1);
        btnRe2 = findViewById(R.id.btnRespuesta2);
        btnRe3 = findViewById(R.id.btnRespuesta3);
        btnRe4 = findViewById(R.id.btnRespuesta4);

        numUser = getIntent().getStringExtra("num_user");

        numAleatorio();
        nivel();
    }

    // Calcular la experiencia ganada por cada nivel
    private int experiencia(int nivel, int expActual)
    {
        if ( nivel < 20 )
        {
            return expActual += 125;
        }
        if ( nivel > 20 && nivel < 40 )
        {
            return expActual += 504;
        }
        if ( nivel > 60 )
        {
            return expActual += 755;
        }
        return 0;
    }


    // Método que revisa el nivel del usuario
    private int nivel()
    {
        // Conexión a base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BD", null, 1);
        // Modo de escritura y lectura de la base de datos
        SQLiteDatabase BD = admin.getWritableDatabase();
        // Traer con un intent el número de usuario seleccionado según el botón
        String nivelJugador = getIntent().getStringExtra("num_user");
        Cursor consulta = BD.rawQuery(
                "select niv_res, exp from jugador where num_user = " + nivelJugador, null);
        if ( consulta.moveToFirst() )
        {
            nivel = Integer.parseInt(consulta.getString(0));
            int exp = Integer.parseInt(consulta.getString(1));
            tvNivel.setText("Nivel " + nivel);
            return exp;
        }
        return 0;
    }

    public void revisar(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BD", null, 1);
        SQLiteDatabase BD = admin.getWritableDatabase();

        if ( view.getId() == btnRe1.getId() && btnCorrecto == 0 )
        {
            startActivity(new Intent(this, Revision_Correcta.class));
            numAleatorio();
            nivel++;
            tvNivel.setText(String.valueOf("Nivel " + nivel));

            ContentValues nivelCompletado = new ContentValues();
            nivelCompletado.put("niv_res", nivel);
            nivelCompletado.put("exp", experiencia(nivel, nivel()));
            BD.update("jugador", nivelCompletado, "num_user = " + numUser, null);
        }
        else if ( view.getId() == btnRe2.getId() && btnCorrecto == 1 )
        {
            startActivity(new Intent(this, Revision_Correcta.class));
            numAleatorio();
            nivel++;
            tvNivel.setText(String.valueOf("Nivel " + nivel));

            ContentValues nivelCompletado = new ContentValues();
            nivelCompletado.put("niv_res", nivel);
            nivelCompletado.put("exp", experiencia(nivel, nivel()));
            BD.update("jugador", nivelCompletado, "num_user = " + numUser, null);
        }
        else if ( view.getId() == btnRe3.getId() && btnCorrecto == 2 )
        {
            startActivity(new Intent(this, Revision_Correcta.class));
            numAleatorio();
            nivel++;
            tvNivel.setText(String.valueOf("Nivel " + nivel));

            ContentValues nivelCompletado = new ContentValues();
            nivelCompletado.put("niv_res", nivel);
            nivelCompletado.put("exp", experiencia(nivel, nivel()));
            BD.update("jugador", nivelCompletado, "num_user = " + numUser, null);
        }
        else if ( view.getId() == btnRe4.getId() && btnCorrecto == 3 )
        {
            startActivity(new Intent(this, Revision_Correcta.class));
            numAleatorio();
            nivel++;
            tvNivel.setText(String.valueOf("Nivel " + nivel));

            ContentValues nivelCompletado = new ContentValues();
            nivelCompletado.put("niv_res", nivel);
            nivelCompletado.put("exp", experiencia(nivel, nivel()));
            BD.update("jugador", nivelCompletado, "num_user = " + numUser, null);
        }
        else
        {
            vidas--;
            startActivity(new Intent(this, Revision_Incorrecta.class));
            comparar(vidas);
        }
    }

    private void comparar(int vidas)
    {
        switch ( vidas )
        {
            case 4:
                Toast.makeText(this, "Te quedan 4 vidas - ¡Tú puedes!", Toast.LENGTH_SHORT).show();
                ivVidas.setImageResource(R.drawable.vidas_cuatro);
                break;
            case 3:
                Toast.makeText(this, "Te quedan 3 vidas - ¡Tú puedes!", Toast.LENGTH_SHORT).show();
                ivVidas.setImageResource(R.drawable.vidas_tres);
                break;
            case 2:
                Toast.makeText(this, "Te quedan 2 vidas - ¡Tú puedes!", Toast.LENGTH_SHORT).show();
                ivVidas.setImageResource(R.drawable.vidas_dos);
                break;
            case 1:
                Toast.makeText(this, "Te queda 1 vida - ¡Tú puedes!", Toast.LENGTH_SHORT).show();
                ivVidas.setImageResource(R.drawable.vidas_uno);
                break;
            case 0:
                // Puede ser un snackbar
                Toast.makeText(this, "Te has quedado sin vidas, ve a Teoría a repasar",
                               Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Menu.class);
                intent.putExtra("num_user", numUser);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void numAleatorio()
    {
        // Creación de números aleatorios del 0 al 9
        numAleatorioUno = (int) (Math.random() * 20);
        numAleatorioDos = (int) (Math.random() * 20);

        if ( numAleatorioUno > numAleatorioDos )
        {
            resultado = numAleatorioUno - numAleatorioDos;
            if ( resultado < 11 && resultado > 3 )
            {
                tvSumando1.setText(String.valueOf(numAleatorioUno));
                tvSumando2.setText(String.valueOf(numAleatorioDos));
                // Asginar el número de botón de la respuesta correcta
                btnCorrecto = (int) (Math.random() * 3);
                switch ( btnCorrecto )
                {
                    case 0:
                        btnRe1.setText(String.valueOf(resultado));
                        btnRe2.setText(String.valueOf(resultado + 3));
                        btnRe3.setText(String.valueOf(resultado - 2));
                        btnRe4.setText(String.valueOf(resultado + 2));
                        break;
                    case 1:
                        btnRe2.setText(String.valueOf(resultado));
                        btnRe1.setText(String.valueOf(resultado + 2));
                        btnRe3.setText(String.valueOf(resultado - 1));
                        btnRe4.setText(String.valueOf(resultado + 3));
                        break;
                    case 2:
                        btnRe3.setText(String.valueOf(resultado));
                        btnRe1.setText(String.valueOf(resultado - 2));
                        btnRe2.setText(String.valueOf(resultado + 1));
                        btnRe4.setText(String.valueOf(resultado - 3));
                        break;
                    case 3:
                        btnRe4.setText(String.valueOf(resultado));
                        btnRe1.setText(String.valueOf(resultado + 2));
                        btnRe3.setText(String.valueOf(resultado + 1));
                        btnRe2.setText(String.valueOf(resultado - 1));
                        break;
                }
            }
            else
            {
                numAleatorio();
            }
        }
        else
        {
            numAleatorio();
        }
    }

    public void teoria(View view)
    {
        Toast.makeText(this, "No disponible", Toast.LENGTH_SHORT).show();
    }

    public void pista(View view)
    {
        Toast.makeText(this, "No disponible", Toast.LENGTH_SHORT).show();
    }

    // Botón regresar
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, Menu.class);
        intent.putExtra("num_user", numUser);
        startActivity(intent);
        finish();
    }
}