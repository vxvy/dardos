package com.example.dardos.escenas;

import android.app.Activity;
import android.content.Context;
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
import com.example.dardos.elementos.Boton;

import java.util.ArrayList;

import static com.example.dardos.codeUtils.Constantes.BTN_SALIDA_YES_ID;
import static com.example.dardos.codeUtils.Constantes.ESCENA_MENU_VALUE;

public class EscenaPopSalida extends EsquemaEscena {

    public Paint backgroundPaint;
    public Paint fontPaint;

    public Rect rectanguloContenedor;

    public ArrayList<Boton> arlBotonnes;

    public int auxH;
    public int auxV;

    public EscenaPopSalida(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);

        this.auxH = anchoPantalla/5;
        this.auxV = altoPantalla/11;

        arlBotonnes = new ArrayList<Boton>();
        arlBotonnes.add(
                new Boton(
                        this.auxH, auxV *4,
                        this.auxH *2, auxV *5,
                        Color.WHITE,
                        false,
                        Constantes.BTN_TEXT_Y,
                        Color.BLACK,
                        BTN_SALIDA_YES_ID)
        );
        arlBotonnes.add(
                new Boton(
                        this.auxH *3, auxV *4,
                        this.auxH *4, auxV *5,
                        Color.WHITE,
                        false,
                        Constantes.BTN_TEXT_N,
                        Color.BLACK,
                        Constantes.BTN_SALIDA_NO_ID)
        );

        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.DKGRAY);
        backgroundPaint.setAlpha(20);

        fontPaint = new Paint();
        fontPaint.setTypeface(Typeface.createFromAsset(context.getAssets(), AssetsPaths.FONT_EPIC_PATH));
//        fontPaint.setColor(this.context.getColor(R.color.papiro1));
        fontPaint.setColor(MainActivity.context.getColor(R.color.papiro1));
        fontPaint.setTextSize(auxV);

        rectanguloContenedor = new Rect(auxH, auxV,auxH*4, auxV*6);
    }

    @Override
    public void escenaDibuja(Canvas c) {
        c.drawRect(0,0,anchoPantalla, altoPantalla, backgroundPaint);
        c.drawRect(rectanguloContenedor, new Paint());

        c.drawText(MainActivity.context.getString(R.string.msg_exit),
                auxH*2, auxV*3, fontPaint);

        for (Boton b:arlBotonnes) {
            b.dibujaBoton(c);
        }
    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        if(event.getActionMasked() == MotionEvent.ACTION_UP){
            for(Boton b:arlBotonnes){
                if(b.pulsaBoton(event) && b.btnValor == BTN_SALIDA_YES_ID){
                    ((Activity)context).finish();
                }
//                else if(b.pulsaBoton(event) && b.btnValor == BTN_SALIDA_NO_ID){
                else{
                    return ESCENA_MENU_VALUE;
                }
            }
        }
        return super.onTouchEvent(event);
    }

}