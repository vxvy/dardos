package com.example.dardos.escenas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.MotionEvent;

import com.example.dardos.MainActivity;
import com.example.dardos.R;
import com.example.dardos.codeUtils.AssetsPaths;
import com.example.dardos.codeUtils.RecursosCodigo;
import com.example.dardos.elementos.Boton;

import java.util.ArrayList;

import static com.example.dardos.codeUtils.Constantes.ESCENA_CREDITOS_VALUE;
import static com.example.dardos.codeUtils.Constantes.ESCENA_DIFICULTAD_VALUE;
import static com.example.dardos.codeUtils.Constantes.ESCENA_SALIR_VALUE;


public class EscenaMenu extends EsquemaEscena {

    ArrayList<Boton> btnColection;

    String strTitulo;
    Paint paintTituloStroke;
    Paint paintTituloFill;

    //posiciones relativas de esta pantalla, se instanciar
    int auxV;
    int auxH;

    public Bitmap bmFondo;

    public EscenaMenu(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);

        this.auxV = altoPantalla/9;
        this.auxH = anchoPantalla/3;

        //Titulo
        strTitulo = context.getString(R.string.app_name);
        paintTituloFill = new Paint();
        paintTituloFill.setTextSize(auxV);
        paintTituloFill.setTypeface(Typeface.createFromAsset(context.getAssets(), AssetsPaths.FONT_EPIC_PATH));
        paintTituloFill.setColor(Color.BLACK);
        paintTituloFill.setStyle(Paint.Style.FILL);
        paintTituloStroke = new Paint();
        paintTituloStroke.setTextSize(auxV);
        paintTituloStroke.setTypeface(Typeface.createFromAsset(context.getAssets(), AssetsPaths.FONT_EPIC_PATH));
        paintTituloStroke.setColor(context.getColor(R.color.greenyhard));
        paintTituloStroke.setStyle(Paint.Style.STROKE);
        paintTituloStroke.setStrokeWidth(1);

        //Botones
        btnColection = new ArrayList<>();
        btnColection.add(
                new Boton(
                        auxH, auxV*3,
                        auxH*2, auxV*4,
                        Color.TRANSPARENT,
                        true,
                        context.getString(R.string.btn_play),
                        context.getColor(R.color.papiro1),
                        ESCENA_DIFICULTAD_VALUE));
        btnColection.add(
                new Boton(
                        auxH,auxV *5,
                        auxH*2, auxV*6,
                        Color.TRANSPARENT,
                        true,
                        context.getString(R.string.btn_credits),
                        context.getColor(R.color.papiro2),
                        ESCENA_CREDITOS_VALUE));
        btnColection.add(
                new Boton(
                        auxH,auxV*7,
                        auxH*2,auxV*8,
                        Color.TRANSPARENT,
                        true,
                        context.getString(R.string.btn_exit),
                        context.getColor(R.color.papiro1),
                        ESCENA_SALIR_VALUE));

        this.bmFondo = RecursosCodigo.getBitmapFromAssets(context,AssetsPaths.BACKGROUND01_BROWN_PATH);
        bmFondo = Bitmap.createScaledBitmap(
                bmFondo,
                anchoPantalla,altoPantalla,
                false);

        MainActivity.mediaPlayer = MediaPlayer.create(context, R.raw.mainmenu);
        int volumen = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        MainActivity.mediaPlayer.setVolume(volumen/2,volumen/2);
        MainActivity.mediaPlayer.isLooping();
        MainActivity.mediaPlayer.start();
    }

    @Override
    public void escenaDibuja(Canvas c) {
        c.drawBitmap(bmFondo,0,0,null);
        c.drawText(strTitulo, auxH, auxV*2, paintTituloFill);
        c.drawText(strTitulo, auxH, auxV*2, paintTituloStroke);
        for(Boton b : btnColection){
            b.dibujaBoton(c);
        }
    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                for(Boton b : btnColection){
                    if(b.pulsaBoton(event)){
                        MainActivity.mediaPlayer.pause();
                        return b.btnValor;
                    }
                }
            break;
        }
        return idEscena; //si el método pudiese ser void no sería necesario esto, pero no puede ser :c
    }
}