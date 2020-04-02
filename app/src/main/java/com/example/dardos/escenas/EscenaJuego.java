package com.example.dardos.escenas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.dardos.EscenaEnPantalla;
import com.example.dardos.R;
import com.example.dardos.codeUtils.AssetsPaths;
import com.example.dardos.codeUtils.Constantes;
import com.example.dardos.codeUtils.RecursosCodigo;

import static com.example.dardos.codeUtils.AssetsPaths.BACKGROUND03_MAINGAME_PATH;
import static com.example.dardos.codeUtils.Constantes.DIFF_EASY;
import static com.example.dardos.codeUtils.Constantes.DIFF_EASY_VEL;
import static com.example.dardos.codeUtils.Constantes.DIFF_HARD;
import static com.example.dardos.codeUtils.Constantes.DIFF_HARD_VEL;
import static com.example.dardos.codeUtils.Constantes.DIFF_MED;
import static com.example.dardos.codeUtils.Constantes.DIFF_MED_VEL;
import static com.example.dardos.codeUtils.Constantes.ESCENA_GAME_OVER;

public class EscenaJuego extends EsquemaEscena {

    public Context context;

    //velocidades
    public int velDardoX;
    public int velDardoXActual;
    public int velDardoY;

    //tamaño dianas
    public float radCircle1; //interior
    public float radCircle2; //
    public float radCircle3; //
    public float radCircle4; //
    public float radCircle5; //exterior

    //paint para todo_
    public Paint circlePaint;

    //bm de assets
    public Bitmap dart;
    public Bitmap cross;
    public Bitmap bmFondo;

    //proporciones que se usan en esta pantalla de carga
    public int auxH;
    public int auxV;

    //Movimientos del dardo
    public boolean dardoMovH;
    public boolean dardoLanzar;
    public boolean finLanzamiento;
    public boolean aumentaX;

    //pos dardo
    public int dartPosXi;
    public int dartPosXf;
    public int dartPosYi;
    public int dartPosYf;
    public Rect dartRect;

    //pos cruceta
    public int crossPosXi;
    public int crossPosXf;
    public int crossPosYi;
    public int crossPosYf;
    public Rect crossRect;

    //num lanzamientos
    public int numLanzamientos;
    public static int puntuacion;

    public EscenaJuego(Context context, int idEscena, int anchoPantalla, int altoPantalla, int lvlDif) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        this.context = context;

        //el dardo empieza desplazándose hacia la derecha
        this.aumentaX = true;
        this.dardoMovH = true;
        this.dardoLanzar = false;
        this.finLanzamiento = true;

        //Velocidades
        switch (lvlDif){
            case DIFF_EASY:
                this.velDardoX = DIFF_EASY_VEL;
                this.velDardoXActual = DIFF_EASY_VEL;
                break;
            case DIFF_MED:
                this.velDardoX = DIFF_MED_VEL;
                this.velDardoXActual = DIFF_MED_VEL;
                break;
            case DIFF_HARD:
                this.velDardoX = DIFF_HARD_VEL;
                this.velDardoXActual = DIFF_HARD_VEL;
                break;
        }
        velDardoY = Constantes.VERTICAL_VEL;

        //puntuación jugador
        this.numLanzamientos = 5;
        this.puntuacion = 0;

        //propiedades pantalla
        this.auxH = anchoPantalla/12;
        this.auxV = altoPantalla/3;

        this.radCircle5 = auxH*5;
        this.radCircle4 = auxH*4;
        this.radCircle3 = auxH*3;
        this.radCircle2 = auxH*2;
        this.radCircle1 = auxH;

        this.circlePaint = new Paint();

        this.dart = RecursosCodigo.getBitmapFromAssets(context,AssetsPaths.DART_BM);
        this.dart = Bitmap.createScaledBitmap(dart, auxH, auxH*2, false);

        this.cross = RecursosCodigo.getBitmapFromAssets(context,AssetsPaths.DART_THROW_BM);
        this.cross = Bitmap.createScaledBitmap(cross,auxH/2,auxH/2,false);

        this.setInitialPosForEverything();

        this.bmFondo = RecursosCodigo.getBitmapFromAssets(context,BACKGROUND03_MAINGAME_PATH);
        this.bmFondo = Bitmap.createScaledBitmap(bmFondo,anchoPantalla,altoPantalla,false);
    }

    public void setInitialPosForEverything(){
        this.dartPosXi=(auxH*6)-(this.dart.getWidth()/2);
        this.dartPosXf=this.dartPosXi+dart.getWidth();
        this.dartPosYi=(auxV*3)-(this.dart.getHeight()/2) - auxH;
        this.dartPosYf=this.dartPosYi+dart.getHeight();
        this.dartRect = new Rect(
                dartPosXi,dartPosYi,
                dartPosXf,dartPosYf);

        this.crossPosXi=(auxH*6)-(this.cross.getWidth()/2);
        this.crossPosXf=this.crossPosXi+cross.getWidth();
        this.crossPosYi=auxV - (this.cross.getHeight()/2);
        this.crossPosYf=this.crossPosYi+cross.getHeight();
        this.crossRect = new Rect(
                dartPosXi,dartPosYi,
                dartPosXf,dartPosYf);
    }

    @Override
    public void escenaDibuja(Canvas c) {
        c.drawRect(0, 0,
                anchoPantalla, altoPantalla,
                new Paint()
        ); //Con esto pinto toda la pantalla de negro para evitar solapamientos raros

        c.drawBitmap(bmFondo,0,0,null);

        //dibuja puntuación y tal
        this.circlePaint.setTextSize(anchoPantalla/15);
        this.circlePaint.setColor(Color.WHITE);
        c.drawText("Vidas: "+numLanzamientos,auxH,anchoPantalla/15,circlePaint);

        c.drawText("Puntuación: "+puntuacion,auxH, anchoPantalla/15 *2, circlePaint);

        this.circlePaint = new Paint();

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

        c.drawBitmap(this.dart,
                 this.dartPosXi,
                 this.dartPosYi,
                null);

        c.drawBitmap(this.cross,
                 this.crossPosXi,
                 this.crossPosYi,
                null);

        super.escenaDibuja(c);
    }

    /**
     * Se lanza el dardo cuando se toca en el tercio inferior
     * de la pantalla dispuesta en vertical
     * @param event
     * @return en caso de pulsar el botón "atrás", se vuelve al menú principal
     */
    @Override
    public int onTouchEvent(MotionEvent event) {
        if(event.getY() > auxV*2){
            this.dardoLanzar = true;
            this.finLanzamiento = false;
            this.dardoMovH = false;
        }

        if(numLanzamientos <= 0){
            return ESCENA_GAME_OVER;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void escenaActFisicas() {
        if(dardoLanzar && !finLanzamiento && numLanzamientos > 0){
            this.dartPosYi-=velDardoY;
            this.dartPosYf-=velDardoY;
            if(this.dartPosYi <= (this.crossPosYf - this.dart.getHeight()/2)){
                this.compruebaDisparo();
                this.finLanzamiento = true;
                this.numLanzamientos--;
                this.setInitialPosForEverything();
                RecursosCodigo.espera(1000);
            }
        }else if(numLanzamientos > 0){
            if (aumentaX) {
                this.dartPosXi += velDardoX;
                this.dartPosXf += velDardoX;

                this.crossPosXi += velDardoX;
                this.crossPosXf += velDardoX;

                if (this.dartPosXf > anchoPantalla) {
                    aumentaX = false;
                }
            } else {
                this.dartPosXi -= velDardoX;
                this.dartPosXf -= velDardoX;

                this.crossPosXi -= velDardoX;
                this.crossPosXf -= velDardoX;

                if (this.dartPosXi < 0) {
                    aumentaX = true;
                }
            }
        }else{

        }
    }

    public void compruebaDisparo(){
        //la posición central de los círculos corresponde a auxH*6
        int centroDisparo = dartPosXi - dart.getWidth()/2;

        Log.d("WHERE",
                "Centro disparo: "+centroDisparo+
                "\nRango puntuación: " + (auxH*6 - radCircle1) + " - "+ (auxH*6 + radCircle1)+
                "\nCentro círculo " + auxH*6 + " - radio círculo "+radCircle1
        );

        if(centroDisparo >= 0 && centroDisparo < auxH
            || centroDisparo > auxH*9 && centroDisparo <= auxH*10){
            puntuacion += 1;
        }
//        else if(centroDisparo >= auxH *2 && centroDisparo < auxH*3
//            || centroDisparo > auxH*9 && centroDisparo <= auxH*10){
//            puntuacion+=2;
//        }
//        else if(centroDisparo >= auxH *3 && centroDisparo < auxH*4
//            || centroDisparo > auxH*8 && centroDisparo <= auxH*9){
//            puntuacion+=3;
//        }
//        else if(centroDisparo >= auxH *4 && centroDisparo < auxH*5
//            || centroDisparo > auxH*7 && centroDisparo <= auxH*8){
//            puntuacion+=4;
//        }
//        else if(centroDisparo >= auxH *5 && centroDisparo < auxH*6
//                || centroDisparo >= auxH*6 && centroDisparo <= auxH*7){
//            puntuacion+=5;
//        }
//        else if(dartPosXi/2){
//
//        }
//        else if(dartPosXi/2){
//
//        }
//        else if(dartPosXi/2){
//
//        }
        //else falla y no pasa nada con los puntos
    }
}