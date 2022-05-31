package com.equipo1.matexpres;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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

/**
 * TODO: Ventana emergente con mensaje de respuesta correcta o incorrecta
 * TODO: Revisar el nivel actual del usuario y subir el nivel de dificultad
 * TODO: Agregar Popup de introducción según nuevo tema o Principiante/Intermedio/Avanzado
 */

public class Suma extends AppCompatActivity
{
    private ImageView ivVidas, infinito;
    private TextView tvNivel, tvSumando1, tvSumando2, tvModo;
    private Button btnRe1, btnRe2, btnRe3, btnRe4, teoria, pista;
    private boolean activacion = true;
    private ConstraintLayout fondo;

    int numAleatorioUno, numAleatorioDos, resultado, vidas = 5, nivel;
    int btnCorrecto;
    String numUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suma);

        ivVidas = findViewById(R.id.vidas);
        tvNivel = findViewById(R.id.nivel);
        tvSumando1 = findViewById(R.id.sumando1);
        tvSumando2 = findViewById(R.id.sumando2);
        tvModo = findViewById(R.id.tvModo);
        btnRe1 = findViewById(R.id.btnRespuesta1);
        btnRe2 = findViewById(R.id.btnRespuesta2);
        btnRe3 = findViewById(R.id.btnRespuesta3);
        btnRe4 = findViewById(R.id.btnRespuesta4);
        fondo = findViewById(R.id.fondo);
        infinito = findViewById(R.id.infinito);
        teoria = findViewById(R.id.btnTeoria);
        pista = findViewById(R.id.btnPista);

        numUser = getIntent().getStringExtra("num_user");

        nivel();
    }

    // Calcular la experiencia ganada por cada nivel
    private int experiencia(int nivel, int expActual)
    {
        if ( nivel < 20 )
        {
            return expActual += 125;
        }
        if ( nivel > 20 )
        {
            return expActual += 504;
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
                "select niv_sum, exp from jugador where num_user = " + nivelJugador, null);
        if ( consulta.moveToFirst() )
        {
            nivel = Integer.parseInt(consulta.getString(0));
            if ( nivel == 0 )
            {
                // Introducción
                nivelBasico();
                startActivity(new Intent(this, Introduccion.class));
                tvModo.setText("Suma > Principiante");
            }
            else if ( nivel > 0 && nivel < 3 )
            {
                nivelBasico();
                tvModo.setText("Suma > Principiante");
            }
            else if ( nivel > 2 && nivel < 5 )
            {
                nivelMedio();
                tvModo.setText("Suma > Intermedio");
            }
            else if ( nivel > 4 && nivel < 9 )
            {
                nivelAvanzado();
                tvModo.setText("Suma > Avanzado");
            }
            else
            {
                desbSuper();
                tvModo.setText("Suma > Infinito");
                nivelAvanzado();
            }
            int exp = Integer.parseInt(consulta.getString(1));
            tvNivel.setText("Nivel " + nivel);
            return exp;
        }
        return 0;
    }

    private void desbSuper()
    {
        if ( activacion )
        {
            fondo.setBackground(getResources().getDrawable(R.drawable.fondo_supervivencia));
            Toast.makeText(this, "Nivel Infinito Desbloqueado", Toast.LENGTH_SHORT).show();
            infinito.setVisibility(View.VISIBLE);
            teoria.setVisibility(View.INVISIBLE);
            pista.setVisibility(View.INVISIBLE);
            tvNivel.setVisibility(View.INVISIBLE);
            activacion = false;
        }
    }

    private void nivelSuper()
    {
        // Creación de números aleatorios del 0 al 100
        numAleatorioUno = (int) (Math.random() * 100);
        numAleatorioDos = (int) (Math.random() * 100);

        resultado = numAleatorioUno + numAleatorioDos;
        if ( resultado < 101 && resultado > 3 )
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
            nivelSuper();
        }
    }

    public void revisar(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BD", null, 1);
        SQLiteDatabase BD = admin.getWritableDatabase();

        if ( view.getId() == btnRe1.getId() && btnCorrecto == 0 )
        {
            startActivity(new Intent(this, Revision_Correcta.class));
            nivel();
            nivel++;
            tvNivel.setText(String.valueOf("Nivel " + nivel));

            ContentValues nivelCompletado = new ContentValues();
            nivelCompletado.put("niv_sum", nivel);
            nivelCompletado.put("exp", experiencia(nivel, nivel()));
            BD.update("jugador", nivelCompletado, "num_user = " + numUser, null);
        }
        else if ( view.getId() == btnRe2.getId() && btnCorrecto == 1 )
        {
            startActivity(new Intent(this, Revision_Correcta.class));
            nivel();
            nivel++;
            tvNivel.setText(String.valueOf("Nivel " + nivel));

            ContentValues nivelCompletado = new ContentValues();
            nivelCompletado.put("niv_sum", nivel);
            nivelCompletado.put("exp", experiencia(nivel, nivel()));
            BD.update("jugador", nivelCompletado, "num_user = " + numUser, null);
        }
        else if ( view.getId() == btnRe3.getId() && btnCorrecto == 2 )
        {
            startActivity(new Intent(this, Revision_Correcta.class));
            nivel();
            nivel++;
            tvNivel.setText(String.valueOf("Nivel " + nivel));

            ContentValues nivelCompletado = new ContentValues();
            nivelCompletado.put("niv_sum", nivel);
            nivelCompletado.put("exp", experiencia(nivel, nivel()));
            BD.update("jugador", nivelCompletado, "num_user = " + numUser, null);
        }
        else if ( view.getId() == btnRe4.getId() && btnCorrecto == 3 )
        {
            startActivity(new Intent(this, Revision_Correcta.class));
            nivel();
            nivel++;
            tvNivel.setText(String.valueOf("Nivel " + nivel));

            ContentValues nivelCompletado = new ContentValues();
            nivelCompletado.put("niv_sum", nivel);
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

    private void nivelMedio()
    {
        // Creación de números aleatorios del 10 al 30
        numAleatorioUno = (int) (Math.random() * 30);
        numAleatorioDos = (int) (Math.random() * 30);

        resultado = numAleatorioUno + numAleatorioDos;
        if ( resultado < 30 && resultado > 9 )
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
            nivelMedio();
        }
    }

    private void nivelAvanzado()
    {
        // Creación de números aleatorios del 30 al 50
        numAleatorioUno = (int) (Math.random() * 50);
        numAleatorioDos = (int) (Math.random() * 50);
        resultado = numAleatorioUno + numAleatorioDos;
        if ( resultado < 50 && resultado > 29 )
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
            nivelAvanzado();
        }
    }

    private void nivelBasico()
    {
        // Creación de números aleatorios del 0 al 9
        numAleatorioUno = (int) (Math.random() * 9);
        numAleatorioDos = (int) (Math.random() * 9);

        resultado = numAleatorioUno + numAleatorioDos;
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
            nivelBasico();
        }
    }

    public void teoria(View view)
    {
        Intent intent = new Intent(this, Teoria.class);
        startActivity(intent);
        finish();
    }

    public void pista(View view)
    {
        startActivity(new Intent(this, Pista.class));
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