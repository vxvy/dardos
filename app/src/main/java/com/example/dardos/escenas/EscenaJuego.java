package com.example.dardos.escenas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import com.example.dardos.R;
import com.example.dardos.codeUtils.AssetsPaths;
import com.example.dardos.codeUtils.RecursosCodigo;
import com.example.dardos.elementos.Boton;

import java.util.ArrayList;
import java.util.TimerTask;

import static com.example.dardos.codeUtils.AssetsPaths.BACKGROUND03_MAINGAME_PATH;
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

    public float radCircle1; //interior
    public float radCircle2; //
    public float radCircle3; //
    public float radCircle4; //
    public float radCircle5; //exterior

    public Paint circlePaint;

    public Bitmap dart;
    public Bitmap cross;
    public Bitmap bmFondo;

    public int auxH;
    public int auxV;

    public EscenaJuego(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        this.context = context;

        this.auxH = anchoPantalla/12;
        this.auxV = altoPantalla/3;

        this.radCircle5 = auxH*5;
        this.radCircle4 = auxH*4;
        this.radCircle3 = auxH*3;
        this.radCircle2 = auxH*2;
        this.radCircle1 = auxH;

        this.circlePaint = new Paint();

        dart = RecursosCodigo.getBitmapFromAssets(context,AssetsPaths.DART_BM);
        dart = Bitmap.createScaledBitmap(dart, auxH, auxH*2, false);

        cross = RecursosCodigo.getBitmapFromAssets(context,AssetsPaths.DART_THROW_BM);
        cross = Bitmap.createScaledBitmap(cross,auxH/2,auxH/2,false);

        bmFondo = RecursosCodigo.getBitmapFromAssets(context,BACKGROUND03_MAINGAME_PATH);
        bmFondo = Bitmap.createScaledBitmap(bmFondo,anchoPantalla,altoPantalla,false);
    }

    @Override
    public void escenaDibuja(Canvas c) {
        c.drawRect(0, 0,
                anchoPantalla, altoPantalla,
                new Paint()
        ); //Con esto pinto toda la pantalla de negro para evitar solapamientos raros

        c.drawBitmap(bmFondo,0,0,null);

//  Esto quedaría mucho mejor en un bucle, la verdad
        this.circlePaint.setColor(context.getColor(R.color.diana5));
        c.drawCircle(auxH*6, auxV, radCircle5, circlePaint);

        circlePaint.setColor(context.getColor(R.color.diana4));
        c.drawCircle(auxH*6, auxV, radCircle4, circlePaint);

        circlePaint.setColor(context.getColor(R.color.diana3));
        c.drawCircle(auxH*6, auxV, radCircle3, circlePaint);

        circlePaint.setColor(context.getColor(R.color.diana2));
        c.drawCircle(auxH*6, auxV, radCircle2, circlePaint);

        circlePaint.setColor(context.getColor(R.color.diana1));
        c.drawCircle(auxH*6, auxV, radCircle1, circlePaint);

        c.drawBitmap(this.cross, (auxH*6)-(this.cross.getWidth()/2), auxV - (this.cross.getHeight()/2), null);

        c.drawBitmap(this.dart, (auxH*6)-(this.cross.getWidth()/2), (auxV*3)-(this.cross.getHeight()*2) - auxH, null);

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