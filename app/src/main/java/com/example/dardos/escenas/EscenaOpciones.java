package com.example.dardos.escenas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.example.dardos.MainActivity;
import com.example.dardos.R;
import com.example.dardos.codeUtils.AssetsPaths;
import com.example.dardos.codeUtils.Constantes;
import com.example.dardos.codeUtils.RecursosCodigo;
import com.example.dardos.elementos.Boton;

import java.util.ArrayList;

public class EscenaOpciones extends EsquemaEscena {

    public Paint fontPaint;
    public Bitmap bmFondo;

    public ArrayList<Boton> arlBotonnes;

    public int auxH;
    public int auxV;

    public EscenaOpciones(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);

        this.auxH =anchoPantalla/5;
        this.auxV = altoPantalla/11;

        arlBotonnes = new ArrayList<Boton>();
        arlBotonnes.add(
                new Boton(
                        this.auxH,
                        auxV * 5,
                        this.auxH *2,
                        auxV * 6,
                        Color.WHITE,
                        false,
                        Constantes.OPCIONES_MUSICA,
                        Color.BLACK,
                        Constantes.OPCIONES_MUSICA_ID)
        );
        arlBotonnes.add(
                new Boton(
                        this.auxH,
                        auxV * 7,
                        this.auxH *2,
                        auxV * 8,
                        Color.WHITE,
                        false,
                        Constantes.OPCIONES_SONIDOS,
                        Color.BLACK,
                        Constantes.OPCIONES_SONIDOS_ID)
        );
        arlBotonnes.add(
                new Boton(
                        this.auxH,
                        auxV * 9,
                        this.auxH *2,
                        auxV * 10,
                        Color.WHITE,
                        false,
                        Constantes.OPCIONES_VIBRACION,
                        Color.BLACK,
                        Constantes.OPCIONES_VIBRACION_ID)
        );
        arlBotonnes.add(btnAtras);

        fontPaint = new Paint();
        fontPaint.setTypeface(Typeface.createFromAsset(context.getAssets(), AssetsPaths.FONT_AWESOME_PATH));
//        fontPaint.setColor(this.context.getColor(R.color.papiro1));
        fontPaint.setColor(Color.WHITE);
        fontPaint.setTextSize(auxV);

        bmFondo = RecursosCodigo.getBitmapFromAssets(this.context, AssetsPaths.BACKGROUND02_GREEN_PATH);
        bmFondo = Bitmap.createScaledBitmap(bmFondo,anchoPantalla,altoPantalla,false);
    }

    @Override
    public void escenaDibuja(Canvas c) {
        c.drawRect(0,0,anchoPantalla, altoPantalla, new Paint());
        c.drawBitmap(
                bmFondo,
                new Rect(0,0,bmFondo.getWidth(),bmFondo.getHeight()),
                new Rect(0,0, anchoPantalla,altoPantalla),
                null
        );

        //music
        CharSequence aux =  MainActivity.musica ?
                context.getString(R.string.opt_music_on_ico) :
                context.getString(R.string.opt_music_off_ico);
        c.drawText(
                aux.toString(),
                this.auxH*3,
                auxV * 6,
                fontPaint);

        for (Boton b:arlBotonnes) {
            b.dibujaBoton(c);
        }
        super.escenaDibuja(c);
    }

    @Override
    public int onTouchEvent(MotionEvent event) {
       if(event.getActionMasked() == MotionEvent.ACTION_UP){
           for(Boton b:arlBotonnes){
               if(b.pulsaBoton(event)){
                   switch (b.btnValor){
                       case Constantes.OPCIONES_MUSICA_ID:
                            MainActivity.musica=!MainActivity.musica;
                           break;
                   }
               }
           }
//            Log.d("qwert","MUSICA: " + MainActivity.musica);
//            Log.d("qwert","EFECTOS: " + MainActivity.efectos);
//            Log.d("qwert","VIB: " + MainActivity.vibracion);
        }

        return super.onTouchEvent(event);
    }
}