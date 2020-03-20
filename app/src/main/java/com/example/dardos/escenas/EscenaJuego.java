package com.example.dardos.escenas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import com.example.dardos.R;
import com.example.dardos.elementos.Boton;

import java.util.ArrayList;
import java.util.TimerTask;

import static com.example.dardos.codeUtils.Constantes.ESCENA_CARGAR_JUEGO;
import static com.example.dardos.codeUtils.Constantes.ESCENA_GAME_OVER;
import static com.example.dardos.codeUtils.Constantes.ESCENA_JUEGO_NIVEL_1_VALUE;


// Esta clase pasará a ser un lanzador para la partida,
// aquí se instanciará el timer que compartirán los distintos niveles.

//El timer se instanció en el main porque si no
// no podía ser accedido por el onResume y otros métodos fácilmente

//Va a haber dos botones, "Nueva partida" y "Continuar"

// Con más tiempo se podría plantear poner dibujitos representativos del estado de la partida aquí

//Este sub-menú tendrá un fondo de parallax

public class EscenaJuego extends EsquemaEscena {


    public Context context;
    public float floorY = 0; //altura del suelo

    public EscenaJuego(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        this.context = context;

        Log.d("QAZ","Cargación");
    }

    @Override
    public void escenaDibuja(Canvas c) {
        c.drawRect(0, 0,
                anchoPantalla, altoPantalla,
                new Paint()
        ); //Con esto pinto toda la pantalla de negro para evitar solapamientos raros
        super.escenaDibuja(c);
    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void escenaActFisicas() {

    }
}