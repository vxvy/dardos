package com.example.dardos.escenas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.example.dardos.R;
import com.example.dardos.codeUtils.AssetsPaths;
import com.example.dardos.codeUtils.Constantes;
import com.example.dardos.codeUtils.RecursosCodigo;


public class EscenaGameOver extends EsquemaEscena {

    public Paint fontPaint;

    public int auxH;
    public int auxV;

    public int puntuacion;

    public EscenaGameOver(Context context, int idEscena, int anchoPantalla, int altoPantalla, int puntuacion) {
        super(context, idEscena, anchoPantalla, altoPantalla);

        this.auxH = anchoPantalla/15;
        this.auxV = altoPantalla/3;

        fontPaint = new Paint();
        fontPaint.setStyle(Paint.Style.FILL);
        fontPaint.setTextSize(auxH);
        fontPaint.setColor(context.getColor(R.color.colisionYes));
        fontPaint.setTypeface(Typeface.createFromAsset(context.getAssets(), AssetsPaths.FONT_MAIN_TITLE_PATH));

        this.puntuacion = puntuacion;
    }

    @Override
    public void escenaDibuja(Canvas c) {
        c.drawRect(0,auxV,anchoPantalla,auxV*2,new Paint());
        c.drawText(
                context.getString(R.string.msg_gameover)+puntuacion,
                auxH,
                auxV + (auxV/2),
                fontPaint
        );
    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        RecursosCodigo.espera(3000);
        return Constantes.ESCENA_MENU_VALUE;
    }
}
