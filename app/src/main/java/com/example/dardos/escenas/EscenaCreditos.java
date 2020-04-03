package com.example.dardos.escenas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.MotionEvent;

import com.example.dardos.MainActivity;
import com.example.dardos.R;
import com.example.dardos.codeUtils.AssetsPaths;
import com.example.dardos.codeUtils.RecursosCodigo;


public class EscenaCreditos extends EsquemaEscena {

    public Bitmap bmFondo;

    public int auxH;
    public int auxV;

    public int scrollV;

    public String creditosJuego;
    public String creditosMusica;
    public String creditosSonidos;
    public String creditosImagenes1;
    public String creditosImagenes2;
    public String creditosProductor;

    public String creditos[];

    public Paint fontPaint;

    public EscenaCreditos(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);

        this.scrollV = 0;

        this.auxH = anchoPantalla/7;
        this.auxV = altoPantalla/24;

        this.fontPaint = new Paint();
        this.fontPaint.setTypeface(Typeface.createFromAsset(context.getAssets(),AssetsPaths.FONT_EPIC_PATH));
        this.fontPaint.setTextSize(auxV);
        this.fontPaint.setColor(context.getColor(R.color.letrasCreditos));

        this.creditosJuego = "Vanessa Cuartiella";
        this.creditosMusica = "www.wingless-seraph.net";
        this.creditosSonidos = "soundbible.com";
        this.creditosImagenes1 = "Vanessa Cuartiella";
        this.creditosImagenes2 = "www.pexels.com";
        this.creditosProductor = "Javier Conde";

        this.creditos = new String[]{
                context.getString(R.string.credits_author),
                creditosJuego,
                context.getString(R.string.credits_music),
                creditosMusica,
                context.getString(R.string.credits_effects),
                creditosSonidos,
                context.getString(R.string.credits_productor),
                creditosProductor,
                context.getString(R.string.credits_images),
                creditosImagenes1,
                creditosImagenes2
        };


        this.bmFondo = RecursosCodigo.getBitmapFromAssets(context,AssetsPaths.BACKGROUND02_GREEN_PATH);
        bmFondo = Bitmap.createScaledBitmap(
                bmFondo,
                anchoPantalla,altoPantalla,
                false);

        MainActivity.mediaPlayer = MediaPlayer.create(context, R.raw.credits);
        int volumen = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        MainActivity.mediaPlayer.setVolume(volumen/2,volumen/2);
        MainActivity.mediaPlayer.isLooping();
        MainActivity.mediaPlayer.start();
    }

    @Override
    public void escenaDibuja(Canvas c) {
        c.drawRect(new Rect(0,0,anchoPantalla, altoPantalla), new Paint());

        c.drawBitmap(
                bmFondo,
                new Rect(0,0,bmFondo.getWidth(),bmFondo.getHeight()),
                new Rect(0,0, anchoPantalla,altoPantalla),
                null
        );

        for(int i = 0; i<creditos.length; i++){
            if((auxV*(i+1)+scrollV)>0){ //auxV determina la línea de créditos
                                        //sumamos 1 al índice porque si usamos el 0 no se ve la línea
                                        //se le añade el decremento para que salga de la pantalla eventualmente
                c.drawText(creditos[i], auxH, auxV * (i + 1) + scrollV, fontPaint);
            }else{                      //si se sale de la pantalla al dibujar
//                c.drawText(creditos[i], auxH, auxV * (i + 1) + scrollV, fontPaint);
            }
        }
        super.escenaDibuja(c);
    }

    @Override
    public void escenaActFisicas() {
        if(scrollV <= altoPantalla*-1){ //cuando scrollV sea menor que -1080
            scrollV--;
        }else{ //vuelve a ser 0
            scrollV = 0;
        }
        super.escenaActFisicas();
    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}